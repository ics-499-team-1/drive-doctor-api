package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.VehicleEntity

data class CompletedMaintenance(
    override val id: Int,
    override val name: String,
    override val notes: String?,
    override val pictures: String?,
    val date: String,
    val mileage: Int,
    val serviceCenter: String?,
    val mechanics: String?,
    val totalCost: Double?
) : Maintenance  {
    fun toCompletedMaintenanceEntity(vehicleReference: VehicleEntity) = CompletedMaintenanceEntity (
        name = this.name,
        notes = this.notes,
        pictures = this.pictures,
        date = this.date,
        mileage = this.mileage,
        serviceCenter = this.serviceCenter,
        mechanics = this.mechanics,
        totalCost = this.totalCost,
        vehicle = vehicleReference
        )
}
