package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "upcoming_maintenance")
data class UpcomingMaintenanceEntity(
    @Id
    @GeneratedValue
    val upcomingMaintenanceId: Int = 0,
    override val name: String,
    override val notes: String?,
    override val pictures: String?,
    val mileageInterval: Int?,
    val timeInterval: String?,
    val mileageReminder: Boolean,
    val timeReminder: Boolean,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    val vehicle: VehicleEntity
) : MaintenanceEntity
