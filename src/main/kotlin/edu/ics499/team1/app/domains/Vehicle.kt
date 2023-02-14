package edu.ics499.team1.app.domains

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "vehicle")
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
     val maintenanceHistory: List<CompletedMaintenance>,
     val deactivated: Boolean,
)
