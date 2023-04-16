package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.services.VehicleService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class VehicleControllerTest {
    private val vehicleService: VehicleService = mockk(relaxed = true)
    private val vehicleController = VehicleController(vehicleService)

    private val mockUserId = 123
    private val mockVehicleId = 10
    private val user = UserEntity(
        userId = mockUserId,
        firstName = "John",
        lastName = "Smith",
        email = "johnsmith@email.com",
        phoneNumber = "651-555-4455",
        password = "password"
    )
    private val vehicle = Vehicle(
        name = "My Car",
        year = 2022,
        make = "Toyota",
        model = "Corolla",
        trim = "LE",
        odometer = 5000,
        licensePlateNumber = "ABC123",
        vin = "12345678901234567",
        userId = user.userId
    )
    private val vehicleEntity = VehicleEntity(
        vehicleId = mockVehicleId,
        name = vehicle.name,
        year = vehicle.year,
        make = vehicle.make,
        model = vehicle.model,
        trim = vehicle.trim,
        odometer = vehicle.odometer,
        licensePlateNumber = vehicle.licensePlateNumber,
        vin = vehicle.vin,
        deactivated = false,
        user = user
    )

    @Test
    fun `getVehicles() works as expected`() {
        // given
        val expectedResponse = listOf(vehicleEntity)

        // when
        every { vehicleService.getVehicles() } returns expectedResponse
        val actualResponse = vehicleController.getVehicles()

        // then
        verify(exactly = 1) { vehicleService.getVehicles() }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `getVehicle() works as expected`() {
        // given
        val expectedResponse = Optional.of(vehicleEntity)

        // when
        every { vehicleService.getVehicle(mockVehicleId) } returns expectedResponse
        val actualResponse = vehicleController.getVehicle(mockVehicleId)

        // then
        verify(exactly = 1) { vehicleService.getVehicle(mockVehicleId) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `addVehicle() works as expected`() {
        // given
        val expectedResponse = vehicleEntity

        // when
        every { vehicleController.addVehicle(vehicle) } returns expectedResponse
        val actualResponse = vehicleController.addVehicle(vehicle)

        // then
        verify(exactly = 1) { vehicleService.createVehicle(vehicle) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `removeVehicle() test`() {
        // given
        val expectedResponse = Unit

        // when
        every { vehicleController.removeVehicle(mockVehicleId) } returns expectedResponse
        val actualResponse = vehicleController.removeVehicle(mockVehicleId)

        // then
        verify(exactly = 1) { vehicleController.removeVehicle(mockVehicleId) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()

    }
}