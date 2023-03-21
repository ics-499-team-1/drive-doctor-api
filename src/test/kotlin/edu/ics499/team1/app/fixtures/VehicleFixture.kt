package edu.ics499.team1.app.fixtures

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.VehicleEntity
import kotlin.random.Random

class VehicleFixture {

    companion object {
        fun vehicleEntity(
            vehicleId: Int = Random.nextInt(),
            name: String = "vehicle$vehicleId",
            year: Int = 1,
            make: String = "make",
            model: String = "model",
            trim: String = "trim",
            odometer: Int = 1,
            licensePlateNumber: String? = null,
            vin: String? = null,
            deactivated: Boolean = false,
            userId: Int = 1
        ): VehicleEntity {
            return VehicleEntity(
                vehicleId = vehicleId,
                name = name,
                year = year,
                model = model,
                make = make,
                trim = trim,
                odometer = odometer,
                licensePlateNumber = licensePlateNumber,
                vin = vin,
                deactivated = deactivated,
                user = UserFixture.user(userId = userId)
            )
        }

        fun vehicleDomain(
            name: String = "vehicle",
            year: Int = 1920,
            make: String = "make",
            model: String = "model",
            trim: String = "trim",
            odometer: Int = 1,
            licensePlateNumber: String? = null,
            vin: String? = null,
            deactivated: Boolean = false,
            userId: Int = 1
        ): Vehicle {
            return Vehicle(
                name = name, year = year, make = make, model = model, trim = trim, odometer = odometer,
                licensePlateNumber = licensePlateNumber, vin = vin, deactivated = deactivated, userId = userId

            )
        }
    }
}