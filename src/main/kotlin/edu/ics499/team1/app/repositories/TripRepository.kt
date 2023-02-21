package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

// custom queries by https://www.baeldung.com/spring-data-jpa-modifying-annotation
interface TripRepository : JpaRepository<TripEntity, Int> {

    @Modifying
    @Query("update TripEntity set mileage = :mileage where tripId = :tripId")
    fun updateMileage(@Param("tripId") tripId: Int, @Param("mileage") mileage: Int)

    fun findAllByVehicle(vehicle: VehicleEntity): List<TripEntity>
}
