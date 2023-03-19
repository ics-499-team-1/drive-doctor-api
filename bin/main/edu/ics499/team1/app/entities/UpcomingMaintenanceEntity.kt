package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "upcoming_maintenance")
data class UpcomingMaintenanceEntity(
    @Id
    @GeneratedValue
    val maintenanceId: Int,
    val name: String,
    val notes: String?,
    val pictures: String?,
    val mileageInterval: Int?,
    val timeInterval: String?,
    val mileageReminder: Boolean,
    val timeReminder: Boolean,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    val vehicle: VehicleEntity
)
