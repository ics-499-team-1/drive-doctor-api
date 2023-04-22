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
            mileageInterval: Int? = null,
            timeInterval: String? = null,
            mileageReminder: Boolean = false,
            timeReminder: Boolean = false
        ): UpcomingMaintenanceEntity {
            return UpcomingMaintenanceEntity(
                upcomingMaintenanceId = maintenanceId, name = name, notes = notes, mileageInterval = mileageInterval,
                timeInterval = timeInterval, mileageReminder = mileageReminder, timeReminder = timeReminder,
                VehicleFixture.vehicleEntity()
            )
        }

        fun upcomingMaintenanceDomain(
            name: String = "Jack Handy",
            notes: String? = null,
            mileageInterval: Int? = null,
            timeInterval: String? = null,
            mileageReminder: Boolean = false,
            timeReminder: Boolean = false,
        ): UpcomingMaintenance {
            return UpcomingMaintenance(
                name = name, notes = notes, mileageInterval = mileageInterval, timeInterval = timeInterval,
                mileageReminder = mileageReminder, timeReminder = timeReminder,
            )
        }
    }
}