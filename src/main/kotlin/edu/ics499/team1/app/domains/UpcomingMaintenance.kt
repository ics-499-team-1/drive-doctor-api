package edu.ics499.team1.app.domains

data class UpcomingMaintenance(
    override val name: String,
    override val notes: String,
    override val pictures: String,
    val mileageInterval: Int,
    val timeInterval: String,
    val mileageReminder: Boolean,
    val timeReminder: Boolean
) : Maintenance
