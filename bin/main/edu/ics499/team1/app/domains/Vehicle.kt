package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity

data class Vehicle(
    val name: String,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val odometer: Int,
    val licensePlateNumber: String?,
    val vin: String?,
    val deactivated: Boolean = false,
    val userId: Int
) {
    fun toVehicleEntity(userReference: UserEntity) = VehicleEntity(
        name = this.name,
        year = this.year,
        make = this.make,
        model = this.model,
        trim = this.trim,
        odometer = this.odometer,
        licensePlateNumber = this.licensePlateNumber,
        vin = this.vin,
        deactivated = this.deactivated,
        user = userReference
    )
}
