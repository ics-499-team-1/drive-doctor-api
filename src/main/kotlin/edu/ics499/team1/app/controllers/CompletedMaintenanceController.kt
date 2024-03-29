package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.services.CompletedMaintenanceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

// TODO: Get a single maintenance?
// TODO: Update a maintenance item?
// TODO: Exception Handlers
/**
 * [getCompletedMaintenancesByVehicleId] Returns all of a vehicle's completed maintenance records
 * [addMaintenance] Adds a completed maintenance record
 * [deleteCompletedMaintenance] Removes a maintenance record
 */
@RestController
@RequestMapping("/v1/maintenance/completed-maintenance")
class CompletedMaintenanceController(private val service: CompletedMaintenanceService) {

    /**
     * Gets all maintenance records for a specific vehicle.
     * @param vehicleId
     * @return The List<CompletedMaintenanceEntity> for the specified vehicle.
     */
    @GetMapping("/vehicles/{vehicleId}")
    fun getCompletedMaintenancesByVehicleId(@PathVariable vehicleId: Int) =
        service.getCompletedMaintenanceByVehicleId(vehicleId)

    /**
     * Creates a new completed maintenance record for a specific vehicle.
     * @param vehicleId
     * @param completedMaintenance
     * @return Unit
     */
    @PostMapping("/vehicles/{vehicleId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMaintenance(
        @PathVariable vehicleId: Int,
        @RequestBody completedMaintenance: CompletedMaintenance
    ): CompletedMaintenanceEntity = service.createCompletedMaintenance(vehicleId, completedMaintenance)

    /**
     * Deletes a completed maintenance entity with the specified ID
     * @param maintenanceId
     */
    @DeleteMapping("/{maintenanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCompletedMaintenance(@PathVariable maintenanceId: Int) = service.removeCompletedMaintenance(maintenanceId)

    // todo There is probably a more robust way to do this, if we want to adapt it for all fields
    /**
     * Updates the name field of the upcoming maintenance item.
     */
    @PatchMapping("/{maintenanceId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCompletedMaintenance(
        @PathVariable maintenanceId: Int,
        @RequestBody completedMaintenance: CompletedMaintenance
    ) = service.updateCompletedMaintenance(maintenanceId, completedMaintenance)
}
