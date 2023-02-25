package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.domains.VehicleTripMileage
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.TripRepository
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TripService(
    private val tripRepository: TripRepository,
    private val vehicleRepository: VehicleRepository,
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
            totalMiles = trips.sumOf { it.mileage },
            trips = trips
        )
    }

    fun getUserTrips(user: UserEntity): List<TripEntity> {
        // Find vehicles of user
        val vehicles = vehicleRepository.findByUser(user)

        // Initialize list to store trips
        val trips = mutableListOf<TripEntity>()

        // Loop through vehicles and find trips of each vehicle
        for(vehicle in vehicles){
            val vehicleTrips = tripRepository.findAllByVehicle(vehicle)
            trips.addAll(vehicleTrips)
        }

        return trips
    }
}
