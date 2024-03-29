package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CompletedMaintenanceServiceTest {
    private val completedMaintenanceRepository: CompletedMaintenanceRepository = mockk(relaxed = true)
    private val vehicleRepository: VehicleRepository = mockk(relaxed = true)
    private val completedMaintenanceService =
        CompletedMaintenanceService(completedMaintenanceRepository, vehicleRepository)

    @Test
    fun `createCompletedMaintenance() working as expected`() {
        //given
        val user = UserEntity(
            userId = 1,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@email.com",
            phoneNumber = "651-555-4455",
            vehicles = emptyList(),
            password = "password"
        )
        val vehicle = VehicleEntity(
            vehicleId = 1,
            name = "My Car",
            year = 2022,
            make = "Toyota",
            model = "Corolla",
            trim = "LE",
            odometer = 5000,
            licensePlateNumber = "ABC123",
            vin = "12345678901234567",
            deactivated = false,
            user = user
        )

        val maintenance = CompletedMaintenance(
            name = "Oil Change",
            notes = null,
            date = "3/12/2023",
            mileage = 101000,
            mechanics = "Self",
            serviceCenter = null,
            totalCost = 63.5,
        )
        //when
        every { vehicleRepository.getReferenceById(vehicle.vehicleId) } returns vehicle
        every { completedMaintenanceRepository.save(maintenance.toCompletedMaintenanceEntity(vehicle)) } returns maintenance.toCompletedMaintenanceEntity(
            vehicle
        )
        val actualResponse = completedMaintenanceService.createCompletedMaintenance(vehicle.vehicleId, maintenance)
        //then
        verify(exactly = 1) { vehicleRepository.getReferenceById(vehicle.vehicleId) }
        verify(exactly = 1) { completedMaintenanceRepository.save(maintenance.toCompletedMaintenanceEntity(vehicle)) }
        assertEquals(maintenance.toCompletedMaintenanceEntity(vehicle), actualResponse)
    }

    @Test
    fun `getCompletedMaintenanceByVehicleId() works as expected`() {
        // given
        val user = UserEntity(
            userId = 1,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@email.com",
            phoneNumber = "651-555-4455",
            vehicles = emptyList(),
            password = "password"
        )

        val vehicle = VehicleEntity(
            vehicleId = 1,
            name = "My Car",
            year = 2022,
            make = "Toyota",
            model = "Corolla",
            trim = "LE",
            odometer = 5000,
            licensePlateNumber = "ABC123",
            vin = "12345678901234567",
            deactivated = false,
            user = user
        )

        val maintenance = CompletedMaintenance(
            name = "Oil Change",
            notes = null,
            date = "3/12/2023",
            mileage = 101000,
            mechanics = "Self",
            serviceCenter = null,
            totalCost = 63.5,
        )

        val completedMaintenanceEntity = maintenance.toCompletedMaintenanceEntity(vehicle)
        val completedMaintenanceList = listOf(completedMaintenanceEntity)

        every { vehicleRepository.getReferenceById(vehicle.vehicleId).completedMaintenance } returns completedMaintenanceList

        // when
        val result = completedMaintenanceService.getCompletedMaintenanceByVehicleId(vehicle.vehicleId)

        // then
        verify(exactly = 1) { vehicleRepository.getReferenceById(vehicle.vehicleId).completedMaintenance }
        assertEquals(completedMaintenanceList, result)
    }

    @Test
    fun `removeCompletedMaintenance() works as expected`() {
        // given
        val maintenanceId = 1
        every { completedMaintenanceRepository.deleteById(maintenanceId) } returns Unit

        // when
        completedMaintenanceService.removeCompletedMaintenance(maintenanceId)

        // then
        verify { completedMaintenanceRepository.deleteById(maintenanceId) }
        confirmVerified(completedMaintenanceRepository)
    }

    // TODO: needs to test the completed maintenance SERVICE NOT the completed maintenance REPOSITORY
    @Test
    fun `updateCompletedMaintenanceName() as expected`() {
        // given
        val user = UserEntity(
            userId = 1,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@email.com",
            phoneNumber = "651-555-4455",
            vehicles = emptyList(),
            password = "password"
        )

        val vehicle = VehicleEntity(
            vehicleId = 1,
            name = "My Car",
            year = 2022,
            make = "Toyota",
            model = "Corolla",
            trim = "LE",
            odometer = 5000,
            licensePlateNumber = "ABC123",
            vin = "12345678901234567",
            deactivated = false,
            user = user
        )

        val completedMaintenance = CompletedMaintenance(
            name = "Oil Change",
            notes = null,
            date = "3/12/2023",
            mileage = 101000,
            mechanics = "Self",
            serviceCenter = null,
            totalCost = 63.5,
        )

        val completedMaintenanceEntity = completedMaintenance.toCompletedMaintenanceEntity(vehicle)
        val newName = "New Name"

        val updatedCompletedMaintenanceDomain = CompletedMaintenance(
            name = newName,
            notes = null,
            date = "3/12/2023",
            mileage = 101000,
            mechanics = "Self",
            serviceCenter = null,
            totalCost = 63.5,
        )

        every {
            completedMaintenanceService.updateCompletedMaintenance(
                maintenanceId = completedMaintenanceEntity.completedMaintenanceId,
                completedMaintenance = updatedCompletedMaintenanceDomain
            )
        } returns Unit

        // when
        val response =
            completedMaintenanceService.updateCompletedMaintenance(
                maintenanceId = completedMaintenanceEntity.completedMaintenanceId,
                completedMaintenance = updatedCompletedMaintenanceDomain
            )

        // then
        verify(exactly = 1) {
            completedMaintenanceService.updateCompletedMaintenance(
                maintenanceId = completedMaintenanceEntity.completedMaintenanceId,
                completedMaintenance = updatedCompletedMaintenanceDomain
            )
        }
        assertEquals(response, Unit)
    }
}