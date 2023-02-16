package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val vehicles: List<VehicleEntity>
)
