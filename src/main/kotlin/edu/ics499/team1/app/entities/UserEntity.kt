package edu.ics499.team1.app.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue
    val userId: Int,
    val name: String,
    val email: String,
    val phoneNumber: String?
)
