package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.UserRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class UserServiceTest {

    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserService(userRepository)

    @Test
    fun `createUser() called with successful response`() {
        // given
        val userRequest = User("John", "Doe", "johndoe@email.com", null)
        val expectedResponse = userRequest.toUserEntity()

        // when
        every { userRepository.save(userRequest.toUserEntity()) } returns expectedResponse
        val actualResponse = userService.createUser(userRequest)

        // then
        verify(exactly = 1) { userRepository.existsByFirstNameAndLastNameAndEmail(userRequest.firstName, userRequest.lastName, userRequest.email) }
        verify(exactly = 1) { userRepository.save(userRequest.toUserEntity()) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUser() is called with successful response`() {
        // given
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = Optional.of(user)

        // when
        every { userRepository.findById(user.userId) } returns expectedResponse
        val actualResponse = userService.getUser(user.userId)

        // then
        verify(exactly = 1) { userRepository.findById(user.userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUsers() is called with successful response`() {
        // given
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = listOf(user)

        // when
        every { userRepository.findAll() } returns expectedResponse
        val actualResponse = userService.getUsers()

        // then
        verify(exactly = 1) { userRepository.findAll() }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `deleteUser() is called with successful response`() {
        // given
        val userId = 123

        // when
        userService.deleteUser(userId)

        // then
        verify(exactly = 1) { userRepository.deleteById(userId) }

        confirmVerified()
    }

    @Test
    fun `getUserVehicles() is called with successful response`() {
        // given
        val userId = 123
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = user.vehicles

        // when
        every { userRepository.findById(userId) } returns Optional.of(user)
        val actualResponse = userService.getUserVehicles(userId)

        // then
        verify(exactly = 1) { userRepository.findById(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUserTrips() is called with successful response`() {
        // given
        val userId = 123
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = emptyList<TripEntity>()

        // when
        every { userRepository.getReferenceById(userId) } returns user
        val actualResponse = userService.getUserTrips(userId)

        // then
        verify(exactly = 1) { userRepository.getReferenceById(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }
}