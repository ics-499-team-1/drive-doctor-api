package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.UpcomingMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.stereotype.Service

@Service
class UpcomingMaintenanceService(
    private val upcomingMaintenanceRepository: UpcomingMaintenanceRepository,
    private val vehicleRepository: VehicleRepository,
    private val completedMaintenanceRepository: CompletedMaintenanceRepository) {
    /**
     * Service for returning upcoming maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @return List<UpcomingMaintenanceEntity>
     */
    fun getUpcomingMaintenanceByVehicleId(vehicleId: String): List<UpcomingMaintenanceEntity> =
        vehicleRepository.getReferenceById(vehicleId.toInt()).upcomingMaintenance
    /**
     * Service for creating upcoming maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @param maintenance Of domain type UpcomingMaintenance
     * @return Unit
     */
    fun createUpcomingMaintenance(vehicleId: String, maintenance: UpcomingMaintenance) {
        val vehicle = vehicleRepository.getReferenceById(vehicleId.toInt())
        upcomingMaintenanceRepository.save(maintenance.toUpcomingMaintenanceEntity(vehicle))
    }

    /**
     * Deletes an upcoming maintenance item.
     * @param maintenanceId
     * @return Unit
     */
    fun removeUpcomingMaintenance(maintenanceId: String) =
        upcomingMaintenanceRepository.deleteById(maintenanceId.toInt())

    /**
     * Updates the name field in an existing maintenance item.
     * @param maintenanceId
     * @param name
     * @return Unit
     */
    fun updateUpcomingMaintenanceName(maintenanceId: String, name: String) {
        upcomingMaintenanceRepository.modifyUpcomingMaintenanceName(maintenanceId.toInt(), name)

    }

    /**
     * Calls the db and get an UpcomingMaintenanceEntity with the corresponding id. It takes the name, notes,
     * picutres, and vehicle fields from this entity and uses it to build a CompletedMaintenanceEntity, combining those
     * fields with the remaining required fields from the completeMaintenance object.
     * It then saves the CompletedMaintenanceEntity in the db and deletes the record of the UpcomingMaintenanceEntity.
     * @param maintenanceId The id of the upcoming maintenance to be converted
     * @param completedMaintenance The post body to update the item with
     */
    fun convertUpcomingToCompleted(maintenanceId: String, completedMaintenance: CompletedMaintenance) {
        val upcoming = upcomingMaintenanceRepository.getReferenceById(maintenanceId.toInt())
        val completed = CompletedMaintenanceEntity (
            name = upcoming.name,
            notes = upcoming.notes,
            pictures = upcoming.pictures,
            date = completedMaintenance.date,
            mileage = completedMaintenance.mileage,
            serviceCenter = completedMaintenance.serviceCenter,
            mechanics = completedMaintenance.mechanics,
            totalCost = completedMaintenance.totalCost,
            vehicle = upcoming.vehicle
        )
        completedMaintenanceRepository.save(completed)
        upcomingMaintenanceRepository.deleteById(upcoming.maintenanceId)
    }


}
