package edu.ics499.team1.app.entities

import jakarta.persistence.*

@Entity
@Table(name = "vehicles")
data class VehicleEntity(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val year: Int,
    val make: String,
    val trim: String,
    val odometer: Int,
    val licensePlateNumber: String?,
    val vin: String?,
    val deactivated: Boolean,
    @ManyToOne
    val user: UserEntity,
    @OneToMany(mappedBy = "vehicle", cascade = [CascadeType.ALL])
    val trips: List<TripEntity>
)
