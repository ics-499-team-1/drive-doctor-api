package edu.ics499.team1.app.fixtures

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.VehicleEntity
import kotlin.random.Random

class CompletedMaintenanceFixture {

    companion object {
        fun completedMaintenanceEntity(
            maintenanceId: Int = Random(4).nextInt(),
            name: String = "Jack Handy",
            notes: String? = null,
            date: String = "mm/dd/yyyy",
            mileage: Int = 1,
            serviceCenter: String? = null,
            mechanics: String? = null,
            totalCost: Double = 1.0,
            vehicle: VehicleEntity = VehicleFixture.vehicleEntity()
        ): CompletedMaintenanceEntity {
            return CompletedMaintenanceEntity(
                completedMaintenanceId = maintenanceId, name = name, notes = notes, date = date, mileage = mileage,
                serviceCenter = serviceCenter, mechanics = mechanics, totalCost = totalCost, vehicle = vehicle
            )
        }

        fun completedMaintenanceDomain(
            name: String = "Jack Handy",
            notes: String? = null,
            date: String = "mm/dd/yyyy",
            mileage: Int = 1,
            serviceCenter: String? = null,
            mechanics: String? = null,
            totalCost: Double = 1.0,
        ): CompletedMaintenance {
            return CompletedMaintenance(
                name = name, notes = notes, date = date, mileage = mileage, serviceCenter = serviceCenter,
                mechanics = mechanics, totalCost = totalCost
            )
        }
    }
}