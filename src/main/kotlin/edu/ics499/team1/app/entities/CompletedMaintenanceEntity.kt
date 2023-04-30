package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "completed_maintenance")
data class CompletedMaintenanceEntity(
    @Id
    @GeneratedValue
    val completedMaintenanceId: Int = 0,
    override val name: String,
    override val notes: String?,
    val date: String,
    val mileage: Int,
    val serviceCenter: String?,
    val mechanics: String?,
    val totalCost: Double?,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    val vehicle: VehicleEntity
) : MaintenanceEntity
