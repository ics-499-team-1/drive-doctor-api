package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.TripEntity
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

    private val userRequest = User("John", "Doe", "johndoe@email.com", "password", "1234567890")
    private val userEntity = userRequest.toUserEntity(password = userRequest.password)
    private val userId = userEntity.userId

    @Test
    fun `getUser() is called with successful response`() {
        // given
        val expectedResponse = Optional.of(userEntity)

        // when
        every { userRepository.findById(userEntity.userId) } returns expectedResponse
        val actualResponse = userService.getUser(userId)

        // then
        verify(exactly = 1) { userRepository.findById(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUsers() is called with successful response`() {
        // given
        val expectedResponse = listOf(userEntity)

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
        // when
        userService.deleteUser(userId)

        // then
        verify(exactly = 1) { userRepository.deleteById(userId) }

        confirmVerified()
    }

    @Test
    fun `getUserVehicles() is called with successful response`() {
        // given
        val expectedResponse = userEntity.vehicles

        // when
        every { userRepository.findById(userId) } returns Optional.of(userEntity)
        val actualResponse = userService.getUserVehicles(userId)

        // then
        verify(exactly = 1) { userRepository.findById(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUserTrips() is called with successful response`() {
        // given
        val expectedResponse = emptyList<TripEntity>()

        // when
        every { userRepository.getReferenceById(userId) } returns userEntity
        val actualResponse = userService.getUserTrips(userId)

        // then
        verify(exactly = 1) { userRepository.getReferenceById(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }
}
