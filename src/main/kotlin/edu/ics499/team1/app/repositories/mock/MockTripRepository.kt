package edu.ics499.team1.app.repositories.mock

import edu.ics499.team1.app.domains.Trip

/*
 val mileage: Int,
    val type: String,
    val vehicleId: Long,
    val name: String?,
    val start: String?,
    val end: String?,
    val notes: String?
 */
class TripRepository {
    private val trips = mutableListOf(
        Trip(0, "Business", 0, "Stuffs", "Eagan", "St. Paul", "Muy importante"),
        Trip(100, "Business", 1, "Meeting with client", "New York", "Boston", "Important meeting"),
        Trip(200, "Personal", 2, "Visit friends", "Los Angeles", "San Francisco", null),
        Trip(300, "Business", 3, "Conference", "Chicago", "Las Vegas", null),
        Trip(400, "Personal", 4, "Road trip", "Miami", "New Orleans", "Fun adventure"),
        Trip(500, "Business", 5, "Sales meeting", "Seattle", "Portland", "Closing the deal")
    )

    fun retrieveTrips(): List<Trip> = trips
}
