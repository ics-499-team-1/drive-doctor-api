package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.services.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
* [getVehicles] Returns a collection of all vehicles
* [addVehicle] Adds a vehicle to the collection
* [removeVehicle] Removes a vehicle from the collection
*/
@RestController
@RequestMapping("/v1/vehicles")  // TODO Is this a logical mapping?
class VehicleController(private val service: VehicleService) {

    @GetMapping
    fun getVehicles() = service.getVehicles()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addVehicle(@RequestBody vehicle: Vehicle) = service.createVehicle(vehicle)

    @DeleteMapping("/{vehicleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeVehicle(@PathVariable vehicleId: Int) = service.deleteVehicle(vehicleId)
}