package edu.ics499.team1.app.domains

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "vehicle")
data class Vehicle(
    private val id: Long,
    private val name: String,
    private val year: Int,
    private val make: String,
    private val trim: String,
    private val odometer: Int,
    private val licensePlateNumber: String?,
    private val vin: String?,
    private val maintenance: List<UpcomingMaintenance>,
    private val maintenanceHistory: List<CompletedMaintenance>,
    private val deactivated: Boolean,
)
