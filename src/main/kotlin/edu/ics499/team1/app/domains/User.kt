package edu.ics499.team1.app.domains

data class User(
    val userId: Int,
    val name: String,
    val email: String,
    val phoneNumber: String?,
    val vehicles: List<Vehicle>,
    val trips: List<Trip>
)
