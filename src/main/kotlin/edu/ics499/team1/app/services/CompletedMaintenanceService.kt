package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.stereotype.Service

@Service
class CompletedMaintenanceService(
    private val completedMaintenance: CompletedMaintenanceRepository,
    private val vehicleRepository: VehicleRepository
) {

    /**
     * Service for returning completed maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @return List<CompletedMaintenanceEntity>
     */
    fun getCompletedMaintenanceByVehicleId(vehicleId: Int): List<CompletedMaintenanceEntity> =
        vehicleRepository.getReferenceById(vehicleId).completedMaintenance

    /**
     * Service for creating completed maintenance items associated with a specific vehicle id.
     * @param vehicleId
     * @param maintenance Of domain type CompletedMaintenance
     * @return Unit
     */
    fun createCompletedMaintenance(vehicleId: String, maintenance: CompletedMaintenance) {
        val vehicle = vehicleRepository.getReferenceById(vehicleId.toInt())
        completedMaintenance.save(maintenance.toCompletedMaintenanceEntity(vehicle))
    }

    /**
     * Deletes an upcoming maintenance item.
     * @param maintenanceId
     * @return Unit
     */
    fun removeCompletedMaintenance(maintenanceId: String) =
        completedMaintenance.deleteById(maintenanceId.toInt())

    /**
     * Updates the name field in an existing maintenance item.
     * @param maintenanceId
     * @param name
     * @return Unit
     */
    fun updateCompletedMaintenanceName(maintenanceId: Int, name: String) {
        completedMaintenance.modifyCompletedMaintenanceName(maintenanceId, name)

    }
}
