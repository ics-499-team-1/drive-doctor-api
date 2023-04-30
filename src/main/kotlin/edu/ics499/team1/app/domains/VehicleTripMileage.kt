package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.TripEntity

data class VehicleTripMileage(
    val vehicleId: Int,
    val vehicleName: String,
    val totalMiles: Int,
    val trips: List<TripEntity>
)
