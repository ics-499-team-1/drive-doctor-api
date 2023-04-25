package edu.ics499.team1.app.consumers

import edu.ics499.team1.app.domains.UpcomingMaintenance

/**
 * Holds the top level JSON of the CarMD API call to the Maint endpoint.
 */
data class CarMDMaintenance(
    val message: CarMDMessage,
    val data: List<CarMDMaintenanceData>
)

/**
 * Structure for the message object in the CarMD API Maint response
 */
data class CarMDMessage(
    val code: Int,
    val message: String,
    val credentials: String,
    val version: String,
    val endpoint: String,
    val counter: Int
)

/**
 * Structure for the data object in the CarMD API Maint response
 */
data class CarMDMaintenanceData(
    val desc: String,
    val due_mileage: Int,
    val repair: CarMDRepairData,
    val parts: List<CarMDPartData>?
)

/**
 * Structure for the repair object in the CarMD API Maint response
 */
data class CarMDRepairData(
    val repair_difficulty: Int,
    val repair_hours: Double,
    val labor_rate_per_hour: Double,
    val part_cost: Double,
    val labor_cost: Double,
    val misc_cost: Double,
    val total_cost: Double
)

/**
 * Structure for the parts object in the CarMD API Maint response
 */
data class CarMDPartData(
    val part_name: String,
    val part_cost: Double
)

/**
 * Parses a CarMDMaintenanceData object into an UpcomingMaintenanceDomain object and returns
 * the UpcomingMaintenanceDomain object..
 */
fun convertCarMDDataToMaintenanceDomain(data: CarMDMaintenanceData): UpcomingMaintenance {
    val upcomingMaintenanceDomain = UpcomingMaintenance(
        name = data.desc,
        notes = "Repair difficulty: ${data.repair.repair_difficulty}\n" +
                "Repair hours: ${data.repair.repair_hours}\n" +
                "Labor rate per hour: ${data.repair.labor_rate_per_hour}\n" +
                "Part cost: ${data.repair.part_cost}\n" +
                "Labor cost: ${data.repair.labor_cost}\n" +
                "Misc cost: ${data.repair.misc_cost}\n" +
                "Total cost: ${data.repair.total_cost}\n" +
                if (data.parts != null) {
                    "Parts: ${data.parts.joinToString { "${it.part_name} (${it.part_cost})" }}"
                } else {
                    ""
                },
        mileageInterval = data.due_mileage,
        timeInterval = null,
        mileageReminder = false,
        timeReminder = false,
    )
    return upcomingMaintenanceDomain;
}
