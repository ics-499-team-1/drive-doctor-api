package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    /**
     * Returns a single UserEntity
     * @param userId An int for the database user_id number
     * @return An Optional<userEntity> matching the userId, or null if no match is found
     * @exception NoSuchElementException Throws if no user with the given userId is matched.
     */
    fun getUser(userId: Int): Optional<UserEntity> {
        val userRep = userRepository.findById(userId)
        if (userRep.isEmpty) {
            throw NoSuchElementException("Could not find a user with userID $userId")
        } else {
            return userRep
        }
    }

    /**
     * Returns a List of all user entities in the db
     * @return List<UserEntity>
     */
    fun getUsers(): List<UserEntity> = userRepository.findAll()

    /**
     * Deletes a user from the database.
     * @param userId An int designating the database user_id number
     * @exception NoSuchElementException Throws if the provided userId is not found in the database.
     */
    fun deleteUser(userId: Int) {
        try {
            userRepository.deleteById(userId)
        } catch (e: EmptyResultDataAccessException) {
            throw NoSuchElementException("No such user with userId $userId exists")
        }
    }

    /**
     * Returns a list of the vehicles associated with the given userId
     * @param userId An int for the database user_id number
     * @return A List<VehicleEntity> associated with the userId
     */
    fun getUserVehicles(userId: Int): List<VehicleEntity> = userRepository.findById(userId).get().vehicles

    fun getUserTrips(userId: Int): List<TripEntity> {
        val trips = mutableListOf<TripEntity>()
        val vehicles = userRepository.getReferenceById(userId).vehicles

        for (vehicle in vehicles) {
            trips.addAll(vehicle.trips)
        }

        return trips
    }
}
