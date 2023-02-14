package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {
   fun findByVehicleId(id: Long): Vehicle?

   fun findByVin(vin: String): Vehicle?
}