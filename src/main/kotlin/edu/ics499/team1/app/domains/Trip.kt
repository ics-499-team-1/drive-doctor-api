package edu.ics499.team1.app.domains

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "trip")
data class Trip(
    val mileage: Int,
    val type: String,
    val vehicleId: Long,
    val name: String?,
    val start: String?,
    val end: String?,
    val notes: String?
)
