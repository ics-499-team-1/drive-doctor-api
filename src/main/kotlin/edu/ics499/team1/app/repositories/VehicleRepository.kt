//package edu.ics499.team1.app.repositories
//
//import edu.ics499.team1.app.domains.Vehicle
//import org.springframework.data.jpa.repository.JpaRepository
//
//interface VehicleRepository : JpaRepository<Vehicle, Long> {
//    fun findByVehicleId(id: Long): Vehicle?
//
//    fun findByVin(vin: String): Vehicle?
//
//    fun findByMakeModel(make: String, model: String): Vehicle?
//
//    fun findByMakeModelTrim(make: String, model: String, trim: String): Vehicle?
//
//    fun findByLicensePlate(licensePlate: String): Vehicle?
//}