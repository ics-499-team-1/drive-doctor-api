package edu.ics499.team1.app.domains.requests

data class Trip(
    val mileage: Int,
    val type: String,
    val vehicleId: Long,
    val name: String?,
    val start: String?,
    val end: String?,
    val notes: String?
)
