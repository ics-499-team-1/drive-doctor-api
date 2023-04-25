package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val userRepository: UserRepository,
    private val upcomingMaintenanceService: UpcomingMaintenanceService
) {
    /**
     * Returns a List of all vehicle entities in the db
     * @return List<VehicleEntity>
     */
    fun getVehicles(): List<VehicleEntity> = vehicleRepository.findAll()

    /**
     * Creates a new Vehicle in the database.
     * @param vehicle: a domain of type Vehicle
     * @exception CustomExceptions.LicensePlateAlreadyExistsException Throws if the license plate number is already
     * in the database.
     * @exception CustomExceptions.VinAlreadyExistsException Throws is the vin number of the vehicle is already
     * found in the database.
     * @return A VehicleEntity of the created vehicle.
     */
    fun createVehicle(vehicle: Vehicle): VehicleEntity {
        if ((vehicle.licensePlateNumber != null &&
                    vehicleRepository.existsByLicensePlateNumber(vehicle.licensePlateNumber))
        )
            throw CustomExceptions.LicensePlateAlreadyExistsException(
                "Vehicle with license plate number " +
                        "${vehicle.licensePlateNumber} already exists"
            )
        if (vehicle.vin != null && vehicleRepository.existsByVin(vehicle.vin))
            throw CustomExceptions.VinAlreadyExistsException("Vehicle with vin ${vehicle.vin} already exists")

        val user = userRepository.getReferenceById(vehicle.userId)
        val newVehicleEntity = vehicleRepository.save(vehicle.toVehicleEntity(user))
        // call to add maintenance items
        upcomingMaintenanceService.upcomingMaintenanceGenerator(newVehicleEntity)
        return newVehicleEntity
    }

    /**
     * Deletes a vehicle from the database.
     * @param vehicleId An int designating the database vehicle_id number
     * @exception NoSuchElementException Throws if the provided vehicle ID is not found in the database.
     */
    fun deleteVehicle(vehicleId: Int) =
        try {
            vehicleRepository.deleteById(vehicleId)
        } catch (e: EmptyResultDataAccessException) {
            throw NoSuchElementException("No such vehicle with vehicleId $vehicleId exists")
        }

    /**
     * Returns a single VehicleEntity
     * @param vehicleId An int for the database vehicle ID number
     * @return A VehicleEntity containing the vehicle ID
     * @exception NoSuchElementException Throws if no vehicle with the given vehicleId can be found.
     */
    fun getVehicle(vehicleId: Int): Optional<VehicleEntity> {
        val vehicleRep = vehicleRepository.findById(vehicleId)
        if (vehicleRep.isEmpty) {
            throw NoSuchElementException("Could not find a vehicle with vehicleID $vehicleId")
        } else {
            return vehicleRep
        }
    }
}
