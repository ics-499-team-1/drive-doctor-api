package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "vehicles")
data class VehicleEntity(
    @Id
    @GeneratedValue
    val vehicleId: Int = 0,
    val name: String,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val odometer: Int,
    val licensePlateNumber: String?,
    val vin: String?,
    val deactivated: Boolean,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val user: UserEntity,
    @OneToMany(mappedBy = "vehicle", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val trips: List<TripEntity> = emptyList(),
    @OneToMany(mappedBy = "vehicle", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val upcomingMaintenance: List<UpcomingMaintenanceEntity> = emptyList(),
    @OneToMany(mappedBy = "vehicle", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val completedMaintenance: List<CompletedMaintenanceEntity> = emptyList(),
)
