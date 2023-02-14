package edu.ics499.team1.app.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

// TODO: Exception Handlers
/**
 * [getVehicles] Returns a collection of all vehicles
 * [addVehicle] Adds a vehicle to the collection
 * [removeVehicle] Removes a vehicle from the collection
 */
@RestController
@RequestMapping("api/vehicles")  // TODO Is this a logical mapping?
class VehicleController (private val service : VehicleService){

    @GetMapping
    fun getVehicles() : Collection<Vehicle> = service.getVehicles()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addVehicle(@RequestBody vehicle : Vehicle) = service.addVehicle(vehicle)

    @DeleteMapping("/{vehicleID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeVehicle(@PathVariable vehicleID : String) = service.removeVehicle(vehicleID)
}