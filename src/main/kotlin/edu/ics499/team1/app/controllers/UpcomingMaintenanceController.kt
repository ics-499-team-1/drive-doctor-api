package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.services.UpcomingMaintenanceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

    // TODO: Get a single maintenance?
    // TODO: Update a maintenance item?
    // TODO: Exception Handlers
    /**
     * [getMaintenance] Returns all maintenance record
     * [addMaintenance] Adds a maintenance record
     * [removeMaintenance] Removes a maintenance record
     */
@RestController
@RequestMapping("/v1/vehicles/maintenance")
class UpcomingMaintenanceController(
    private val service: UpcomingMaintenanceService,

) {

    /**
     * Gets all maintenance records for a specific vehicle.
     */
    @GetMapping("/{vehicleId}/upcomingMaintenance")
    fun getUpcomingMaintenancesByVehicleId(@PathVariable vehicleId: String) = service.getUpcomingMaintenance(vehicleId)

    /**
     * Creates a new maintenance record for a specific vehicle
     */
    @PostMapping("/{vehicleId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMaintenance(@PathVariable vehicleId: String, @RequestBody upcomingMaintenance: UpcomingMaintenance) {
        service.addUpcomingMaintenance(vehicleId, upcomingMaintenance)
    }

    @DeleteMapping("/{vehicleId}/upcomingMaintenance/{maintenanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeMaintenance(@PathVariable vehicleId: String, @PathVariable maintenanceId: String) {
        service.removeUpcomingMaintenance(vehicleId, maintenanceId)
    }


//    @PostMapping // TODO: not sure how to map this yet. Needs own request mapping?
//    @ResponseStatus(HttpStatus.CREATED) // TODO: Could be patch mapped instead?
//    fun completeMaintenance(@PathVariable id: String) {
//        // service.completedMaintenanceRepository(id)
//    }
}