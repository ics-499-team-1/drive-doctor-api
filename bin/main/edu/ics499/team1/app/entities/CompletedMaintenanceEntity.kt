package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "completed_maintenance")
data class CompletedMaintenanceEntity(
    @Id
    val maintenanceId: Int,
    val name: String,
    val notes: String?,
    val pictures: String?,
    val date: String,
    val mileage: Int,
    val serviceCenter: String?,
    val mechanics: String?,
    val totalCost: Double?,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    val vehicle: VehicleEntity
)
