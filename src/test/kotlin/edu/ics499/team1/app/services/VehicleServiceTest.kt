package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class VehicleServiceTest {
    private val vehicleRepository: VehicleRepository = mockk(relaxed = true)
    private val userRepository: UserRepository = mockk(relaxed = true)

    private val vehicleService = VehicleService(vehicleRepository, userRepository)

    private val mockUserId = 123
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
        every { vehicleRepository.findAll() } returns expectedResponse
        val actualResponse = vehicleService.getVehicles()

        // then
        verify(exactly = 1) { vehicleRepository.findAll() }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `createVehicle() works as expected`() {
        // given
        val expectedResponse = vehicle.toVehicleEntity(user)
        every { vehicleRepository.existsByLicensePlateNumber(vehicle.licensePlateNumber) } returns false
        every { vehicleRepository.existsByVin(vehicle.vin) } returns false
        every { userRepository.getReferenceById(mockUserId) } returns user
        every { vehicleRepository.save(expectedResponse) } returns expectedResponse

        // when
        val actualResponse = vehicleService.createVehicle(vehicle)

        // then
        verify(exactly = 1) { userRepository.getReferenceById(vehicle.userId)}
        verify(exactly = 1) { vehicleRepository.save(expectedResponse) }
        verify(exactly = 1) { vehicleRepository.existsByLicensePlateNumber(vehicle.licensePlateNumber) }
        verify(exactly = 1) {  vehicleRepository.existsByVin(vehicle.vin) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }
    
    @Test
    fun `deleteVehicle() works as expected`() {
        // given
        val vehicleId = 1
        val expectedResponse = Unit

        // when
        val actualResponse = vehicleService.deleteVehicle(vehicleId)

        // then
        every { vehicleRepository.deleteById(vehicleId) } returns expectedResponse
        verify(exactly = 1) { vehicleRepository.deleteById(vehicleId) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }
}