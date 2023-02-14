package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Maintenance
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.services.MaintenanceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

class MaintenanceController {

    // TODO: Get a single maintenance?
    // TODO: Update a maintenance item?
    // TODO: Exception Handlers
    /**
     * [getMaintenance] Returns all maintenance record
     * [addMaintenance] Adds a maintenance record
     * [removeMaintenance] Removes a maintenance record
     */
    @RestController
    @RequestMapping("api/{vehicleID}/maintenance")
    class MaintenanceController(
        private val service: MaintenanceService,
    ) {

        /**
         * Gets all maintenance records for a specific vehicle.
         */
        @GetMapping
        fun getMaintenance()  {
            //service.getMaintenance()
        }

        /**
         * Creates a new maintenance record for a specific vehicle
         */
        @PostMapping("/{vehicleId}")
        @ResponseStatus(HttpStatus.CREATED)
        fun addMaintenance(@PathVariable vehicleId: String, @RequestBody maintenance : Maintenance) {
            // service.addMaintenance(vehicleId, maintenance)
        }

        @DeleteMapping("/{vehicleId}/{maintenanceId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun removeMaintenance(@PathVariable vehicleId : String, @PathVariable maintenanceId : String) {
            // service.removeMaintenace(vehicleId, maintenanceId)
        }


        @PostMapping // TODO: not sure how to map this yet. Needs own request mapping?
        @ResponseStatus(HttpStatus.CREATED) // TODO: Could be patch mapped instead?
        fun completeMaintenance(@PathVariable id : String) {
            // service.completedMaintenanceRepository(id)
        }
    }
}