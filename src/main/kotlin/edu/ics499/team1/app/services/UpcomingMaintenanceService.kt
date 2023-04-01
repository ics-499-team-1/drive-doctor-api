package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.UpcomingMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

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
     * @param maintenanceId
     * @param name
     * @return Unit
     */
    fun updateUpcomingMaintenanceEntity(id: Int, upcomingMaintenance: UpcomingMaintenance) {
        upcomingMaintenanceRepository.modifyUpcomingMaintenanceName(
            id,
            upcomingMaintenance.name,
            upcomingMaintenance.notes,
            upcomingMaintenance.pictures,
            upcomingMaintenance.mileageInterval,
            upcomingMaintenance.timeInterval,
            upcomingMaintenance.mileageReminder,
            upcomingMaintenance.timeReminder
        );
    }

    /**
     * Calls the db and get an UpcomingMaintenanceEntity with the corresponding id. It takes the name, notes,
     * picutres, and vehicle fields from this entity and uses it to build a CompletedMaintenanceEntity, combining those
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
            pictures = completedMaintenance.pictures,
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

    fun callUpcomingMaintenanceAPI(): String { // todo: where do I go?
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


}
