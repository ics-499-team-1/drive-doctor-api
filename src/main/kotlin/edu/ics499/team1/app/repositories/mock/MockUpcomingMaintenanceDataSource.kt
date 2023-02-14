package edu.ics499.team1.app.repositiories.mock

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.repositiories.MaintenanceDataSource
import org.springframework.stereotype.Repository

@Repository
class MockUpcomingMaintenanceDataSource : MaintenanceDataSource {
    private val upcomingMaintenance = mutableListOf(
        UpcomingMaintenance("Oil change", "Change oil", "oil.jpg", 3500, "Months", false, false),
        UpcomingMaintenance("Oil change", "Change the oil every 3 months or 3,000 miles", "oil_change.jpg", 3000, "Months", true, false),
        UpcomingMaintenance("Tire rotation", "Rotate the tires every 6 months or 6,000 miles", "tire_rotation.jpg", 6000, "Months", true, false),
        UpcomingMaintenance("Brake pad replacement", "Replace the brake pads every 1 year or 12,000 miles", "brake_pad_replacement.jpg", 12000, "Months", true, false),
        UpcomingMaintenance("Battery replacement", "Replace the battery every 2 years or 24,000 miles", "battery_replacement.jpg", 24000, "Months", true, false),
        UpcomingMaintenance("Spark plug replacement", "Replace the spark plugs every 3 years or 36,000 miles", "spark_plug_replacement.jpg", 36000, "Months", true, false),
    )
    override fun retrieveUpcomingMaintenance(): Collection<UpcomingMaintenance> = upcomingMaintenance
}
