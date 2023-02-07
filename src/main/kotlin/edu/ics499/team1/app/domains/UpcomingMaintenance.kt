package edu.ics499.team1.app.domains

data class UpcomingMaintenance(
    private val mileageInterval: Int,
    private val timeInterval: String,
    private val mileageReminder: Boolean,
    private val timeReminder: Boolean,
)
