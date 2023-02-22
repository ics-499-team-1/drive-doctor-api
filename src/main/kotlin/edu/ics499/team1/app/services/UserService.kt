package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.domains.Vehicle
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

    fun createUser(user: User): UserEntity {
        if ( userRepository.existsByFirstNameAndLastNameAndEmail
                (user.firstName, user.lastName, user.email)) {
            throw CustomExceptions.UserAlreadyExistsException("User already exists in the system")
        }

        return userRepository.save(user.toUserEntity())
    }

    fun getUser(userId: Int): Optional<UserEntity> {
        val userRep = userRepository.findById(userId)
        if (userRep.isEmpty) {
            throw NoSuchElementException("Could not find a user with userID $userId")
        } else {
            return userRep
        }
    }


    fun getUsers(): List<UserEntity> = userRepository.findAll()

    fun deleteUser(userId: Int): Unit =
        try {
            userRepository.deleteById(userId)
        } catch (e: EmptyResultDataAccessException) {
            throw NoSuchElementException("No such user with userId $userId exists")
        }

    fun getUserVehicles(userId: Int) : List<VehicleEntity> = userRepository.findById(userId).get().vehicles

}
