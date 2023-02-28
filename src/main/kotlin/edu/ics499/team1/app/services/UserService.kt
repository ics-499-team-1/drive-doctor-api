package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
<<<<<<< HEAD
import edu.ics499.team1.app.domains.Vehicle
=======
import edu.ics499.team1.app.entities.TripEntity
>>>>>>> main
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import org.springframework.util.AutoPopulatingList.ElementInstantiationException
import java.util.*
import kotlin.NoSuchElementException

@Service
class UserService(private val userRepository: UserRepository) {

    /**
     * Creates a new Vehicle in the database.
     * @param user: a domain of type User
     * @exception CustomExceptions.UserAlreadyExistsException Throws if the combination of firstName, lastName, and
     * email are already in the database.
     * @return A UserEntity of the created user.
     */
    fun createUser(user: User): UserEntity {
        if ( userRepository.existsByFirstNameAndLastNameAndEmail
                (user.firstName, user.lastName, user.email)) {
            throw CustomExceptions.UserAlreadyExistsException("User already exists in the system")
        }

        return userRepository.save(user.toUserEntity())
    }

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

<<<<<<< HEAD
    /**
     * Deletes a user from the database.
     * @param userId An int designating the database user_id number
     * @exception NoSuchElementException Throws if the provided userId is not found in the database.
     */
    fun deleteUser(userId: Int): Unit =
        try {
            userRepository.deleteById(userId)
        } catch (e: EmptyResultDataAccessException) {
            throw NoSuchElementException("No such user with userId $userId exists")
        }
    /**
     * Returns a list of the vehicles associated with the given userId
     * @param userId An int for the database user_id number
     * @return A List<VehicleEntity> associated with the userId
     */
    fun getUserVehicles(userId: Int) : List<VehicleEntity> = userRepository.findById(userId).get().vehicles

=======
    fun deleteUser(userId: Int) = userRepository.deleteById(userId)

    fun getUserTrips(userId: Int): List<TripEntity> {
        val trips = mutableListOf<TripEntity>()
        val vehicles = userRepository.getReferenceById(userId).vehicles
        for (vehicle in vehicles) {
            trips.addAll(vehicle.trips)
        }
        return trips
    }
>>>>>>> main
}
