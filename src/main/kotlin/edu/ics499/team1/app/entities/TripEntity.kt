package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "trips")
data class TripEntity(
    @Id
    @GeneratedValue
    val tripId: Int,
    val mileage: Int,
    val type: String,
    @ManyToOne
    val vehicle: VehicleEntity,
    val name: String?,
    val startLocation: String?,
    val endLocation: String?,
    val notes: String?
)
