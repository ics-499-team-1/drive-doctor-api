package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.domains.VehicleTripMileage
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.repositories.TripRepository
import edu.ics499.team1.app.repositories.VehicleRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TripService(
    private val tripRepository: TripRepository,
    private val vehicleRepository: VehicleRepository,
    private val userService: UserService
) {

    fun getTrips(): List<TripEntity> = tripRepository.findAll()

    fun addTrip(trip: Trip): TripEntity {
        val vehicle = vehicleRepository.getReferenceById(trip.vehicleId)

        return tripRepository.save(trip.toTripEntity(vehicle))
    }

    @Transactional
    fun setMileage(tripId: Int, mileage: Int) {
        tripRepository.updateMileage(tripId, mileage)
    }

    fun getTotalMileage(vehicleId: Int): VehicleTripMileage {
        val vehicle = vehicleRepository.getReferenceById(vehicleId)
        val trips = tripRepository.findAllByVehicle(vehicle)

        return VehicleTripMileage(
            vehicleId = vehicle.vehicleId,
            vehicleName = vehicle.name,
            totalMiles = trips.sumOf { it.mileage },
            trips = trips
        )
    }

    fun getTotalMileageByUser(userId: Int): List<VehicleTripMileage> {
        val vehicles = userService.getUserVehicles(userId)
        val vehicleTripMiles = mutableListOf<VehicleTripMileage>()

        vehicles.forEach { vehicle ->
            val trips = tripRepository.findAllByVehicle(vehicle)
            vehicleTripMiles.add(VehicleTripMileage(
                vehicleId = vehicle.vehicleId,
                vehicleName = vehicle.name,
                totalMiles = trips.sumOf { it.mileage },
                trips = trips
            ))
        }

        return vehicleTripMiles
    }
}
