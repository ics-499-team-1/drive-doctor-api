package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.services.CustomExceptions
import edu.ics499.team1.app.services.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * [getVehicles] Returns a collection of all vehicles
 * [addVehicle] Adds a vehicle to the collection
 * [removeVehicle] Removes a vehicle from the collection
 */
@RestController
@RequestMapping("/v1/vehicles")  // TODO Is this a logical mapping?
class VehicleController(private val service: VehicleService) {

    // Add this method to allow CORS requests
    @Suppress("unused")
    @RequestMapping("/**")
    @CrossOrigin(
        origins = ["http://localhost:5173"],
        allowedHeaders = ["*"],
        methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE]
    )
    fun corsFilter(): String {
        return ""
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(CustomExceptions.VinAlreadyExistsException::class)
    fun handleVinAlreadyExists(e: CustomExceptions.VinAlreadyExistsException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.CONFLICT)

    @ExceptionHandler(CustomExceptions.LicensePlateAlreadyExistsException::class)
    fun handleLicensePlateAlreadyExists(e: CustomExceptions.LicensePlateAlreadyExistsException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.CONFLICT)

    @GetMapping
    fun getVehicles() = service.getVehicles()

    @GetMapping("/{vehicleId}")
    fun getVehicle(@PathVariable vehicleId: Int) = service.getVehicle(vehicleId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addVehicle(@RequestBody vehicle: Vehicle) = service.createVehicle(vehicle)

    @DeleteMapping("/{vehicleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeVehicle(@PathVariable vehicleId: Int) = service.deleteVehicle(vehicleId)
}
