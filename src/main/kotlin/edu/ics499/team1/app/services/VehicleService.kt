package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val userRepository: UserRepository
) {

    fun getVehicles(): List<VehicleEntity> = vehicleRepository.findAll()

    @Transactional
    fun createVehicle(vehicle: Vehicle): VehicleEntity {
        val user = userRepository.getReferenceById(vehicle.userId)
        return vehicleRepository.save(vehicle.toVehicleEntity(user))
    }

    fun deleteVehicle(vehicleId: Int) = vehicleRepository.deleteById(vehicleId)
}
