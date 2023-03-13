package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.TripEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
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

    @Test
    fun `getUser() successful response`() {
        // given
        val userId = 123
        val userEntity = Optional.of(UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList()))

        // when
        every { userService.getUser(123) } returns userEntity
        val actualResponse = userController.getUser(userId)

        // then
        verify(exactly = 1) { userService.getUser(userId) }
        assertEquals(userEntity, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUsers() successful response`() {
        // given
        val userEntityList = listOf(UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList()))

        // when
        every { userService.getUsers() } returns userEntityList
        val actualResponse = userController.getUsers()

        // then
        verify(exactly = 1) { userService.getUsers() }
        assertEquals(userEntityList, actualResponse)

        confirmVerified()
    }

    @Test
    fun `addUser() successful response`() {
        // given
        val userRequest = User("John", "Doe", "johndoe@email.com", null)
        val expectedResponse = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())

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
        // given
        val userId = 123

        // when
        userController.deleteUser(userId)

        // then
        verify(exactly = 1) { userService.deleteUser(userId) }

        confirmVerified()
    }

    @Test
    fun `getUserVehicles() successful response`() {
        // given
        val userId = 123
        val user = UserEntity(userId, "John", "Doe", "johndoe@email.com", null, emptyList())
        val userVehicleList = listOf(VehicleEntity(1, "good car", 2015, "A", "car", "good", 12, null, null, false, user, emptyList(), emptyList(), emptyList()))

        // when
        every { userService.getUserVehicles(userId) } returns userVehicleList
        val actualResponse = userController.getUserVehicles(userId)

        // then
        verify(exactly = 1) { userService.getUserVehicles(userId) }
        assertEquals(userVehicleList, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUserTrips() successful response`() {
        // given
        val userId = 123
        val user = UserEntity(userId, "John", "Doe", "johndoe@email.com", null, emptyList())
        val vehicle = VehicleEntity(1, "good car", 2015, "A", "car", "good", 12, null, null, false, user, emptyList(), emptyList(), emptyList())
        val userTripList = listOf(TripEntity(1, 50, "business", vehicle, null, null, null, null))

        // when
        every { userService.getUserTrips(userId) } returns userTripList
        val actualResponse = userController.getUserTrips(userId)

        // then
        verify(exactly = 1) { userService.getUserTrips(userId) }
        assertEquals(userTripList, actualResponse)

        confirmVerified()
    }
}
