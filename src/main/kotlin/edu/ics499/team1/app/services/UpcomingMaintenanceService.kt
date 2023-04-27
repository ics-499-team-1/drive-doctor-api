package edu.ics499.team1.app.services

import com.google.gson.Gson
import edu.ics499.team1.app.consumers.CarMDMaintenance
import edu.ics499.team1.app.consumers.convertCarMDDataToMaintenanceDomain
import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.UpcomingMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.text.SimpleDateFormat
import java.util.*

@Service
class UpcomingMaintenanceService(
    private val upcomingMaintenanceRepository: UpcomingMaintenanceRepository,
    private val vehicleRepository: VehicleRepository,
    private val completedMaintenanceRepository: CompletedMaintenanceRepository
) {

    /**
     * Service for returning upcoming maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @return List<UpcomingMaintenanceEntity>
     */
    fun getUpcomingMaintenanceByVehicleId(vehicleId: Int): List<UpcomingMaintenanceEntity> =
        vehicleRepository.getReferenceById(vehicleId).upcomingMaintenance

    /**
     * Service for creating upcoming maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @param maintenance Of domain type UpcomingMaintenance
     * @return Unit
     */
    fun createUpcomingMaintenance(vehicleId: Int, maintenance: UpcomingMaintenance) {
        val vehicle = vehicleRepository.getReferenceById(vehicleId)
        upcomingMaintenanceRepository.save(maintenance.toUpcomingMaintenanceEntity(vehicle))
    }

    /**
     * Deletes an upcoming maintenance item.
     * @param maintenanceId
     * @return Unit
     */
    fun removeUpcomingMaintenance(maintenanceId: Int) =
        upcomingMaintenanceRepository.deleteById(maintenanceId)

    /**
     * Updates the name field in an existing maintenance item.
     * @param id
     * @param upcomingMaintenance
     * @return Unit
     */
    fun updateUpcomingMaintenanceEntity(id: Int, upcomingMaintenance: UpcomingMaintenance) {
        upcomingMaintenanceRepository.modifyUpcomingMaintenanceName(
            id,
            upcomingMaintenance.name,
            upcomingMaintenance.notes,
            upcomingMaintenance.mileageInterval,
            upcomingMaintenance.timeInterval,
            upcomingMaintenance.mileageReminder,
            upcomingMaintenance.timeReminder
        )
    }

    /**
     * Calls the db and get an UpcomingMaintenanceEntity with the corresponding id. It takes the name, notes,
     * pictures, and vehicle fields from this entity and uses it to build a CompletedMaintenanceEntity, combining those
     * fields with the remaining required fields from the completeMaintenance object.
     * It then saves the CompletedMaintenanceEntity in the db and deletes the record of the UpcomingMaintenanceEntity.
     * @param maintenanceId The id of the upcoming maintenance to be converted
     * @param completedMaintenance The post body to update the item with
     */
    fun convertUpcomingToCompleted(maintenanceId: Int, completedMaintenance: CompletedMaintenance) {
        val upcoming = upcomingMaintenanceRepository.getReferenceById(maintenanceId)
        val completed = CompletedMaintenanceEntity(
            name = completedMaintenance.name,
            notes = completedMaintenance.notes,
            date = completedMaintenance.date,
            mileage = completedMaintenance.mileage,
            serviceCenter = completedMaintenance.serviceCenter,
            mechanics = completedMaintenance.mechanics,
            totalCost = completedMaintenance.totalCost,
            vehicle = upcoming.vehicle
        )
        completedMaintenanceRepository.save(completed)
        upcomingMaintenanceRepository.deleteById(upcoming.upcomingMaintenanceId)
    }

    /**
     * It calls CARMD and queries the credits.
     */
    fun callAPIForCredits(): String {
        val authorizationKey = System.getenv("CARMD_AUTHORIZATION_KEY")
        val partnerToken = System.getenv("PARTNER_TOKEN")

        val client: WebClient = WebClient.builder()
            .baseUrl("http://api.carmd.com/v3.0")
            .defaultHeader("authorization", authorizationKey)
            .defaultHeader("partner-token", partnerToken)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = client.get()
            .uri("/credits")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        return response ?: ""
    }


    /**
     * Attempts to make a call to the CARMD API using values from the vehicle Entity.
     * If it succeeds, it parses the JSON into  a list of UpcomingMaintenanceDomain objects and then
     * associates them with the vehicle supplied as a list of UpcomingMaintenanceEntity objects.
     * These are then saved in the database.
     * If the call to CARMD fails, it calls defaultMaintenanceGenerator and saves the list of
     * UpcomingMaitnenanceEntity objects returned.
     * @param vehicle VehicleEntity that will receive the maintenance items generated.
     * @return  The List of UpcomingMaintenanceEntity objects created and saved to the repository.
     */
    @Transactional
    fun carMDMaintenanceGenerator(vehicle: VehicleEntity): List<UpcomingMaintenanceEntity> {
        val maintenanceList = ArrayList<UpcomingMaintenanceEntity>()
        var uriString = ""
        val authorizationKey = System.getenv("CARMD_AUTHORIZATION_KEY")
        val partnerToken = System.getenv("PARTNER_TOKEN")

        // Building the WebClient for a CarMD call
        val client: WebClient = WebClient.builder()
            .baseUrl("http://api.carmd.com/v3.0")
            .defaultHeader("authorization", authorizationKey)
            .defaultHeader("partner-token", partnerToken)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
        // checking for which uriString to use
        if (vehicle.vin != null && vehicle.vin.length == 17) {
            uriString = "/maint?vin=${vehicle.vin}&mileage=${vehicle.odometer}"; // set uriString with vin
        } else if (vehicle.year != null && vehicle.make != null && vehicle.model != null) {
            uriString = "/maint?year=${vehicle.year.toString()}&make=${vehicle.make}" +
                    "&model=${vehicle.model}&mileage=${vehicle.odometer}" // set uriString with year/make/model
        }
        // get the response with the uriString
        if (uriString != "") {
            val response = client.get()
                .uri(uriString)
                .retrieve()
                .bodyToMono(String::class.java)
                .block()
            val gson = Gson()
            val carMDMaintenance = gson.fromJson(response, CarMDMaintenance::class.java)
            // is there data? If not we run the default
            if (carMDMaintenance.data != null && carMDMaintenance.message.credentials == "valid") {
                for (item in carMDMaintenance.data) {
                    if (item != null)
                        maintenanceList.add(
                            convertCarMDDataToMaintenanceDomain(item).toUpcomingMaintenanceEntity(
                                vehicle
                            )
                        )
                }
            } else {
                maintenanceList.addAll(defaultUpcomingMaintenanceGenerator(vehicle))  // default added to empty list
            }
        }

        return upcomingMaintenanceRepository.saveAll(maintenanceList)
    }

    /**
     * This is the default maintenance generator for newly created vehicles. Called when calls to the API fail.
     * @param vehicle The vehicle being created.
     */
    private fun defaultUpcomingMaintenanceGenerator(vehicle: VehicleEntity): ArrayList<UpcomingMaintenanceEntity> {
        val maintenanceList = ArrayList<UpcomingMaintenanceEntity>()
        maintenanceList.add(
            UpcomingMaintenance(
                "Oil Change with Filter", "Default", vehicle.odometer - 3000,
                "none", mileageReminder = true, timeReminder = false
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        maintenanceList.add(
            UpcomingMaintenance(
                "Oil Change with Filter", "Default", vehicle.odometer + 3000,
                "none", mileageReminder = true, timeReminder = false
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        maintenanceList.add(
            UpcomingMaintenance(
                "Oil Change with Filter", "Default", vehicle.odometer + 6000,
                "none", mileageReminder = true, timeReminder = false
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        maintenanceList.add(
            UpcomingMaintenance(
                "Blinker Fluid Check", "Default", null,
                dateMaker(14), mileageReminder = false, timeReminder = true
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        maintenanceList.add(
            UpcomingMaintenance(
                "Blinker Fluid Check", "Default", null,
                dateMaker(28), mileageReminder = false, timeReminder = true
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        maintenanceList.add(
            UpcomingMaintenance(
                "Tire Rotation", "Default", vehicle.odometer + 10000,
                dateMaker(180), mileageReminder = true, timeReminder = true
            ).toUpcomingMaintenanceEntity(vehicle)
        )
        return maintenanceList
    }

    /**
     * This is a helper method to convert the current system date to a string format.
     * @param offsetDays - The number of days to offset. Negative numbers work.
     */
    private fun dateMaker(offsetDays: Int): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DAY_OF_MONTH, offsetDays)
        return dateFormat.format(calendar.time)
    }
}
