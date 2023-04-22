package edu.ics499.team1.app.services

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

    fun callAPIForCredits(): String { // todo: where do I go?
        val client: WebClient = WebClient.builder()
            .baseUrl("http://api.carmd.com/v3.0")
            .defaultHeader("authorization", "Basic MjBlY2EyMjItMjc3Ny00MTQxLWJhOGEtNDdlMjhiNGYyNzBl")
            .defaultHeader("partner-token", "24e07ee005cc4294b928ccdc4a4db54c")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
        val response = client.get()
            .uri("/credits")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        return response ?: ""

    }

    @Transactional
    fun upcomingMaintenanceGenerator(vehicle: VehicleEntity, sourceData: MGS): List<UpcomingMaintenanceEntity> {
        val maintenanceList = ArrayList<UpcomingMaintenanceEntity>()
        /**
         * This is the demo mode. It initializes three oil changes, a tire rotation, and 2 blinker fluid changes.
         */
        if (sourceData == MGS.Demo) {
            val odometer = vehicle.odometer
            maintenanceList.add(
                UpcomingMaintenance(
                    "Oil Change with Filter", "Demo", odometer - 3000,
                    "none", mileageReminder = true, timeReminder = false
                ).toUpcomingMaintenanceEntity(vehicle)
            )
            maintenanceList.add(
                UpcomingMaintenance(
                    "Oil Change with Filter", "Demo", odometer + 3000,
                    "none", mileageReminder = true, timeReminder = false
                ).toUpcomingMaintenanceEntity(vehicle)
            )
            maintenanceList.add(
                UpcomingMaintenance(
                    "Oil Change with Filter", "Demo", odometer + 6000,
                    "none", mileageReminder = true, timeReminder = false
                ).toUpcomingMaintenanceEntity(vehicle)
            )
            maintenanceList.add(
                UpcomingMaintenance(
                    "Blinker Fluid Check", "Demo", null,
                    dateMaker(14), mileageReminder = false, timeReminder = true
                ).toUpcomingMaintenanceEntity(vehicle)
            )
            maintenanceList.add(
                UpcomingMaintenance(
                    "Blinker Fluid Check", "Demo", null,
                    dateMaker(28), mileageReminder = false, timeReminder = true
                ).toUpcomingMaintenanceEntity(vehicle)
            )
            maintenanceList.add(
                UpcomingMaintenance(
                    "Tire Rotation", "Demo", vehicle.odometer + 10000,
                    dateMaker(180), mileageReminder = true, timeReminder = true
                ).toUpcomingMaintenanceEntity(vehicle)
            )
        } else if (sourceData == MGS.Live) {
            val client: WebClient = WebClient.builder()
                .baseUrl("http://api.carmd.com/v3.0")
                .defaultHeader("authorization", "Basic MjBlY2EyMjItMjc3Ny00MTQxLWJhOGEtNDdlMjhiNGYyNzBl")
                .defaultHeader("partner-token", "24e07ee005cc4294b928ccdc4a4db54c")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
            if (vehicle.vin != null) {
                val response = client.get()
                    .uri("/maint?vin=${vehicle.vin}&mileage=${vehicle.odometer}")
                    .retrieve()
                    .bodyToMono(String::class.java)
                    .block()
                println(response)
            }
        }
        return upcomingMaintenanceRepository.saveAll(maintenanceList)
    }

    /**
     * Enum for the maintenance generator. Totally unnecessary, but w/e.
     */
    enum class MGS {
        Demo,
        Live
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
