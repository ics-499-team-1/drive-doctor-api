package edu.ics499.team1.app.domains

import jakarta.persistence.Entity

import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "completed-maintenance")
data class CompletedMaintenance(
    @Id
    override val name: String,
    override val notes: String,
    override val pictures: String,
    val date: String,
    val mileage: Int,
    val serviceCenter: String,
    val mechanics: String,
    val totalCost: Int,
) : Maintenance
