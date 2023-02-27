package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class VehicleServiceTest {

    // relaxed set to true means that whenever a method is called on it, it will return some default value
    private val vehicleRepository: VehicleRepository = mockk(relaxed = true)
    private val userRepository: UserRepository = mockk(relaxed = true)

    private val vehicleService = VehicleService(vehicleRepository, userRepository)
   
    @Test
    fun `should call its data source to retrieve vehicles`() {
        // when
        vehicleService.getVehicles()

        // then
        verify(exactly = 1) { vehicleRepository.findAll() }
    }

    @Test
    fun `should create a new vehicle`() {
        // given
        val user = UserEntity(
            userId = 123,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@email.com",
            phoneNumber = "651-555-4455"
        )
        every { userRepository.getReferenceById(any() ) } returns user

        val vehicle = Vehicle(
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
        every { vehicleRepository.save(any<VehicleEntity>()) } returns VehicleEntity(
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

        // when
        val result = vehicleService.createVehicle(vehicle)

        // then
        verify(exactly = 1) { userRepository.getReferenceById(vehicle.userId)}
        verify(exactly = 1) { vehicleRepository.save(any<VehicleEntity>()) }
        assertThat(result.name).isEqualTo(vehicle.name)
        assertThat(result.year).isEqualTo(vehicle.year)
        assertThat(result.make).isEqualTo(vehicle.make)
        assertThat(result.model).isEqualTo(vehicle.model)
        assertThat(result.trim).isEqualTo(vehicle.trim)
        assertThat(result.odometer).isEqualTo(vehicle.odometer)
        assertThat(result.licensePlateNumber).isEqualTo(vehicle.licensePlateNumber)
        assertThat(result.vin).isEqualTo(vehicle.vin)
        assertThat(result.deactivated).isFalse
        assertThat(result.user).isEqualTo(user)
    }
    
    @Test
    fun `should call deleteVehicle() on the vehicle repository`() {
        // given
        val vehicleId = 123
        
        // when
        vehicleService.deleteVehicle(vehicleId)
        
        // then
        verify(exactly = 1) { vehicleRepository.deleteById(vehicleId)}
    }
    
    @Test
    fun `should delete a vehicle by calling deleteVehicle() on the vehicle repo`() {
        // given
        val user = UserEntity(
            userId = 321,
            firstName = "Ryan",
            lastName = "Gallagher",
            email = "ryangalalgher@email.com",
            phoneNumber = "651-555-1234"
        )
        every { userRepository.getReferenceById(any()) } returns user

        val vehicle = Vehicle(
            name = "Test Vehicle",
            year = 2020,
            make = "TestMake",
            model = "TestModel",
            trim = "TestTrim",
            odometer = 123_000,
            licensePlateNumber = "ABC-XYZ",
            vin = "12345678900004567",
            userId = user.userId
        )
        every { vehicleRepository.save(any<VehicleEntity>()) } returns VehicleEntity(
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

        val savedVehicle = vehicleService.createVehicle(vehicle)

        // when
        vehicleService.deleteVehicle(savedVehicle.vehicleId)

        // then
        verify(exactly = 1) { vehicleRepository.deleteById(savedVehicle.vehicleId) }
        every { vehicleRepository.findById(savedVehicle.vehicleId) } returns Optional.empty()
        val deletedVehicle = vehicleRepository.findById(savedVehicle.vehicleId)
        assertEquals(/* expected = */ Optional.empty<VehicleEntity>(), /* actual = */ deletedVehicle)
    }
}
