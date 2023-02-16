package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User) = userRepository.save(user.toUserEntity())

    fun getUser(userId: Int) = userRepository.findById(userId)

    fun getUsers(): List<UserEntity> = userRepository.findAll()

    fun deleteUser(userId: Int) = userRepository.deleteById(userId)
}
