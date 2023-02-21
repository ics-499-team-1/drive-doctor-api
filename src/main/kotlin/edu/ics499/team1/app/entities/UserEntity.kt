package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue
    val userId: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val vehicles: List<VehicleEntity> = emptyList()
)
