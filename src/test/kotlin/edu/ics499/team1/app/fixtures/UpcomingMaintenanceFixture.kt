package edu.ics499.team1.app.fixtures

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import kotlin.random.Random

class UpcomingMaintenanceFixture {

    companion object {
        fun upcomingMaintenanceEntity(
            maintenanceId: Int = Random(4).nextInt(),
            name: String = "Jack Handy",
            notes: String? = null,
            pictures: String? = null,
            mileageInterval: Int? = null,
            timeInterval: String? = null,
            mileageReminder: Boolean = false,
            timeReminder: Boolean = false,
            vehicle: Vehicle = Vehicle(
                "dummy", 1, "none", "none", "none",
                1234, "none", "none", false, Random(4).nextInt()
            )
        ): UpcomingMaintenanceEntity {
            return UpcomingMaintenanceEntity(
                upcomingMaintenanceId = maintenanceId, name = name, notes = notes,
                pictures = pictures, mileageInterval = mileageInterval, timeInterval = timeInterval,
                mileageReminder = mileageReminder, timeReminder = timeReminder,
                VehicleFixture.vehicleEntity()
            )
        }

        fun upcomingMaintenanceDomain(
            name: String = "Jack Handy",
            notes: String? = null,
            pictures: String? = null,
            mileageInterval: Int? = null,
            timeInterval: String? = null,
            mileageReminder: Boolean = false,
            timeReminder: Boolean = false,
        ): UpcomingMaintenance {
            return UpcomingMaintenance(
                id = 1,
                name = name, notes = notes,
                pictures = pictures, mileageInterval = mileageInterval, timeInterval = timeInterval,
                mileageReminder = mileageReminder, timeReminder = timeReminder,
            )
        }
    }
}