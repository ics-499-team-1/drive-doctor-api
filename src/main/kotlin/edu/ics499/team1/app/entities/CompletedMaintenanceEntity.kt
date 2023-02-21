package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "completed_maintenance")
data class CompletedMaintenanceEntity(
    @Id
    val maintenanceId: Int,
    override val name: String,
    override val notes: String?,
    override val pictures: String?,
    val date: String,
    val mileage: Int,
    val serviceCenter: String?,
    val mechanics: String?,
    val totalCost: Double?,
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    val vehicle: VehicleEntity
) : MaintenanceEntity
