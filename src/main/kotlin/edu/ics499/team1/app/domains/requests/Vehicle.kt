package edu.ics499.team1.app.domains.requests

data class Vehicle(
    val id: Long,
    val name: String,
    val year: Int,
    val make: String,
    val trim: String,
    val odometer: Int,
    val licensePlateNumber: String?,
    val vin: String?,
    val maintenance: List<UpcomingMaintenance>,
    val maintenanceRequestHistory: List<CompletedMaintenanceRequest>,
    val deactivated: Boolean
)
