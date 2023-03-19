package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class UserServiceTest {
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserService(userRepository)

    // should this one return all the users too and verify that that has happened
    // or simply verify that findAll() on userRepo has been called?
    @Test
    fun `should call its data source to retrieve users`() {
        // when
        userService.getUsers()

        // then
        verify(exactly = 1) { userRepository.findAll() }
    }
    
    @Test
    fun `should call its data source to retrieve a user by a specific userId`() {
        // given
        val user = UserEntity(
            userId = 1,
            firstName = "Brian",
            lastName = "Griffin",
            email = "bg@email.com",
            phoneNumber = "651-555-6789"
        )
        every { userRepository.getReferenceById(user.userId) } returns user
        
        // when
        val result = userService.getUser(user.userId)
        
        // then
        verify(exactly = 1) { userRepository.findById(user.userId) }
        assertEquals(user.firstName, result.get().firstName)
        assertEquals(user.lastName, result.get().lastName)
        assertEquals(user.email, result.get().email)
        assertEquals(user.phoneNumber, result.get().phoneNumber)
    }

    @Test
    fun `should create a new user by calling UserService's createUser()`() {
        // given


        // when


        // then

    }

    @Test
    fun `should call deleteUser() and a delete a user by their id`() {
        // given
        val user = User(
            firstName = "Peter",
            lastName = "Griffin",
            email = "pg@email.com",
            phoneNumber = "651-555-6111",
        )
        every { userRepository.save(any<UserEntity>()) } returns UserEntity(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phoneNumber = user.phoneNumber,
        )

        val savedUser = userService.createUser(user)

        // when
        userService.deleteUser(savedUser.userId)

        // then
        verify(exactly = 1) { userRepository.deleteById(savedUser.userId) }
        every { userRepository.findById(savedUser.userId) } returns Optional.empty()
        val deletedVehicle = userRepository.findById(savedUser.userId)
        assertEquals(/* expected = */ Optional.empty<UserEntity>(), /* actual = */ deletedVehicle)
    }
}