package edu.ics499.team1.app.domains

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "maintenance")
interface Maintenance {
    val name: String
    val notes: String
    val pictures: String
}
