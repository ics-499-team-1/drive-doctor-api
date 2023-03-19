package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.VehicleEntity

data class Trip(
    val mileage: Int,
    val type: String,
    val vehicleId: Int,
    val name: String?,
    val start: String?,
    val end: String?,
    val notes: String?
) {
    fun toTripEntity(vehicleReference: VehicleEntity) = TripEntity(
        mileage = this.mileage,
        type = this.type,
        vehicle = vehicleReference,
        name = this.name,
        startLocation = this.start,
        endLocation = this.end,
        notes = this.notes
    )
}
