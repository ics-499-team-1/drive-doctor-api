package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import edu.ics499.team1.app.entities.VehicleEntity

data class UpcomingMaintenance(
    override val name: String,
    override val notes: String?,
    val mileageInterval: Int?,
    val timeInterval: String?,
    val mileageReminder: Boolean,
    val timeReminder: Boolean
) : Maintenance {
    fun toUpcomingMaintenanceEntity(vehicleReference: VehicleEntity) = UpcomingMaintenanceEntity(
        name = this.name,
        notes = this.notes,
        mileageInterval = this.mileageInterval,
        timeInterval = this.timeInterval,
        mileageReminder = this.mileageReminder,
        timeReminder = this.timeReminder,
        vehicle = vehicleReference
    )
}
