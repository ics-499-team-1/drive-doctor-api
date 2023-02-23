package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import edu.ics499.team1.app.repositories.UpcomingMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.stereotype.Service

@Service
class UpcomingMaintenanceService(
    private val upcomingMaintenance: UpcomingMaintenanceRepository,
    private val vehicleRepository: VehicleRepository) {

    fun getUpcomingMaintenance(vehicleId: String): List<UpcomingMaintenanceEntity> =
        vehicleRepository.getReferenceById(vehicleId.toInt()).upcomingMaintenance

    fun addUpcomingMaintenance(vehicleId: String, maintenance: UpcomingMaintenance) {
        val vehicle = vehicleRepository.getReferenceById(vehicleId.toInt())
        upcomingMaintenance.save(maintenance.toUpcomingMaintenanceEntity(vehicle))
    }

    fun removeUpcomingMaintenance(vehicleId: String, maintenanceId: String) =
        upcomingMaintenance.deleteById(maintenanceId.toInt())
}
