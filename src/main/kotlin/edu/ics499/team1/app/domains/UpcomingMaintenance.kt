package edu.ics499.team1.app.domains

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "upcoming-maintenance")
data class UpcomingMaintenance(
    private val mileageInterval: Int,
    private val timeInterval: String,
    private val mileageReminder: Boolean,
    private val timeReminder: Boolean,
    override val name: String,
    override val notes: String,
    override val pictures: String,
) : Maintenance
