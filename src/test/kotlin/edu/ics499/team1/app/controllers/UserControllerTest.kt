package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.security.user.Role
import edu.ics499.team1.app.services.UserService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class UserControllerTest {

    private val userService: UserService = mockk(relaxed = true)
    private val userController = UserController(userService)

    private val userId = 123
    private val userRequest = User("John", "Doe", "johndoe@email.com", "password", "1234567890")
    private val userEntity = UserEntity(userId, "John", "Doe", "johndoe@email.com", "password", Role.USER, "1234567890", emptyList())
    private val vehicleEntity = VehicleEntity(1, "good car", 2015, "A", "car", "good", 12, null, null, false, userEntity, emptyList(), emptyList(), emptyList())
    private val tripEntity = TripEntity(1, 50, "business", vehicleEntity, null, null, null, null)

    @Test
    fun `getUser() successful response`() {
        // given
        val expectedResponse = Optional.of(userEntity)

        // when
        every { userService.getUser(userId) } returns expectedResponse
        val actualResponse = userController.getUser(userId)

        // then
        verify(exactly = 1) { userService.getUser(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUsers() successful response`() {
        // given
        val expectedResponse = listOf(userEntity)

        // when
        every { userService.getUsers() } returns expectedResponse
        val actualResponse = userController.getUsers()

        // then
        verify(exactly = 1) { userService.getUsers() }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `addUser() successful response`() {
        // given
        val expectedResponse = userEntity

        // when
        every { userService.createUser(userRequest) } returns expectedResponse
        val actualResponse = userController.addUser(userRequest)

        // then
        verify(exactly = 1) { userService.createUser(userRequest) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `deleteUser() successful response`() {
        // when
        userController.deleteUser(userId)

        // then
        verify(exactly = 1) { userService.deleteUser(userId) }

        confirmVerified()
    }

    @Test
    fun `getUserVehicles() successful response`() {
        // given
        val expectedResponse = listOf(vehicleEntity)

        // when
        every { userService.getUserVehicles(userId) } returns expectedResponse
        val actualResponse = userController.getUserVehicles(userId)

        // then
        verify(exactly = 1) { userService.getUserVehicles(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUserTrips() successful response`() {
        // given
        val expectedResponse = listOf(tripEntity)

        // when
        every { userService.getUserTrips(userId) } returns expectedResponse
        val actualResponse = userController.getUserTrips(userId)

        // then
        verify(exactly = 1) { userService.getUserTrips(userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }
}
