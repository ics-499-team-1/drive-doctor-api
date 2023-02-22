package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val userRepository: UserRepository
) {

    fun getVehicles(): List<VehicleEntity> = vehicleRepository.findAll()

    fun createVehicle(vehicle: Vehicle): VehicleEntity {
        if ((vehicle.licensePlateNumber != null &&
                    vehicleRepository.existsByLicensePlateNumber(vehicle.licensePlateNumber)))
            throw CustomExceptions.LicensePlateAlreadyExistsException("Vehicle with license plate number " +
                    "${vehicle.licensePlateNumber} already exists")
        if (vehicle.vin != null && vehicleRepository.existsByVin(vehicle.vin))
            throw CustomExceptions.VinAlreadyExistsException("Vehicle with vin ${vehicle.vin} already exists")


        val user = userRepository.getReferenceById(vehicle.userId)
        return vehicleRepository.save(vehicle.toVehicleEntity(user))
    }

    fun deleteVehicle(vehicleId: Int)  =
    try {
        vehicleRepository.deleteById(vehicleId)
    } catch (e: EmptyResultDataAccessException) {
        throw NoSuchElementException("No such vehicle with vehicleId $vehicleId exists")
    }

    fun getVehicle(vehicleId: Int): Optional<VehicleEntity> {
        val vehicleRep = vehicleRepository.findById(vehicleId)
        if (vehicleRep.isEmpty) {
            throw NoSuchElementException("Could not find a user with userID $vehicleId")
        } else {
            return vehicleRep
        }
    }
}
