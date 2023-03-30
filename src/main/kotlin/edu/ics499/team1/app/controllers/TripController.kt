package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.services.TripService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

// TODO: Exception Handlers
// TODO: Should getTotalMileage have specific start/end dates?

/**
 * [getTrips] Returns a collection of all trips associated with a specific userID
 * [addTrip] Adds a trip to the specific user's trip collection
 * [setMileage] Sets the mileage for a specific trip
 * [getTotalMileage] gets the totalMileage driven by a specific user
 */
@RestController
@RequestMapping("/v1/trips")
class TripController(private val tripService: TripService) {

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

    @GetMapping
    fun getTrips() = tripService.getTrips()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTrip(@RequestBody trip: Trip) = tripService.addTrip(trip)

    // todo: Should this be in the vehicle controller?
    @PatchMapping("/{tripId}/mileage/{mileage}")
    fun setMileage(@PathVariable tripId: Int, @PathVariable mileage: Int) {
        tripService.setMileage(tripId, mileage)
    }

    @GetMapping("/{vehicleId}/mileage")
    fun getTotalMileage(@PathVariable vehicleId: Int) = tripService.getTotalMileage(vehicleId)
}
