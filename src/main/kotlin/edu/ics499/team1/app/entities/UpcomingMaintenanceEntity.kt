package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "upcoming_maintenance")
data class UpcomingMaintenanceEntity(
    @Id
    @GeneratedValue
    val maintenanceId: Int = 0,
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
