package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.services.UpcomingMaintenanceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

    // TODO: Get a single maintenance?
    // TODO: Update a maintenance item?
    // TODO: Exception Handlers
    /**
     * [getUpcomingMaintenancesByVehicleId] Returns all of a vehicle's maintenance records
     * [addMaintenance] Adds a maintenance record
     * [deleteUpcomingMaintenance] Removes a maintenance record
     */
@RestController
@RequestMapping("/v1/maintenance/upcoming-maintenance")
class UpcomingMaintenanceController(
        private val service: UpcomingMaintenanceService,
) {

    /**
     * Gets all maintenance records for a specific vehicle.
     * @param vehicleId
     * @return The List<UpcomingMaintenanceEntity> for the specified vehicle.
     */
    @GetMapping("/vehicles/{vehicleId}")
    fun getUpcomingMaintenancesByVehicleId(@PathVariable vehicleId: String) = service.getUpcomingMaintenanceByVehicleId(vehicleId)

    /**
     * Creates a new upcoming maintenance record for a specific vehicle.
     * @param vehicleId
     * @param upcomingMaintenance
     * @return Unit
     */
    @PostMapping("/vehicles/{vehicleId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMaintenance(@PathVariable vehicleId: String, @RequestBody upcomingMaintenance: UpcomingMaintenance) {
        service.createUpcomingMaintenance(vehicleId, upcomingMaintenance)
    }

    /**
     * Deletes a vehicle with the specified vehicleId
     * @param vehicleId
     */
    @DeleteMapping("/{maintenanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUpcomingMaintenance(@PathVariable maintenanceId: String) {
        service.removeUpcomingMaintenance(maintenanceId)
    }

        // todo There is probably a more robust way to do this, if we want to adapt it for all fields
        /**
         * Updates the name field of the upcoming maintenance item.
         */
        @PatchMapping("/{maintenanceId}")
        @ResponseStatus(HttpStatus.OK)
        fun updateUpcomingMaintenanceName(@PathVariable maintenanceId: String,
                                      @RequestBody upcomingMaintenance: UpcomingMaintenance) =
            service.updateUpcomingMaintenanceName(maintenanceId, upcomingMaintenance.name)


    @PostMapping("/convert/{maintenanceId}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun convertUpcomingToCompletedMaintenance(@PathVariable maintenanceId: String,
                                              @RequestBody completedMaintenance: CompletedMaintenance) =
        service.convertUpcomingToCompleted(maintenanceId, completedMaintenance)

    @GetMapping("/credits")  //todo: logical end point. Should the service be called in create vehicle?"
    @ResponseStatus(HttpStatus.CREATED)
    fun loadUpcomingMaintenance() = service.callUpcomingMaintenanceAPI() // todo: @PathVariable maintenanceId: String
}
