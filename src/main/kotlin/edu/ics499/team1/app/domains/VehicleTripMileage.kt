package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.TripEntity

data class VehicleTripMileage(
    val totalMiles: Int,
    val trips: List<TripEntity>
)
