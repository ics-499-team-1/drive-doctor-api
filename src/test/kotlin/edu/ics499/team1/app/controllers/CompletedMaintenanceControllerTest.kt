package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.services.CompletedMaintenanceService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CompletedMaintenanceControllerTest {
    private val completedMaintenanceService: CompletedMaintenanceService = mockk(relaxed = true)
    private val completedMaintenanceController = CompletedMaintenanceController(completedMaintenanceService)

    private val user = UserEntity(
        userId = 1,
        firstName = "John",
        lastName = "Smith",
        email = "johnsmith@email.com",
        password = "password",
        phoneNumber = "651-555-4455",
        vehicles = emptyList()
    )

    private val vehicle = VehicleEntity(
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

    private val completedMaintenance = CompletedMaintenance(
        name = "Oil Change",
        notes = null,
        date = "3/12/2023",
        mileage = 101000,
        mechanics = "Self",
        serviceCenter = null,
        totalCost = 63.5,
    )

    private val completedMaintenanceEntity = completedMaintenance.toCompletedMaintenanceEntity(vehicle)

    @Test
    fun `getCompletedMaintenanceByVehicleId() should get all completed maintenance of a vehicle`() {
        // given
        val expectedCompletedMaintenances = listOf(completedMaintenanceEntity)

        // when
        every { completedMaintenanceController.getCompletedMaintenancesByVehicleId(vehicle.vehicleId) } returns expectedCompletedMaintenances
        val result = completedMaintenanceController.getCompletedMaintenancesByVehicleId(vehicle.vehicleId)

        // then
        verify(exactly = 1) { completedMaintenanceController.getCompletedMaintenancesByVehicleId(vehicle.vehicleId) }
        assertEquals(expectedCompletedMaintenances, result)
        confirmVerified()
    }


    //fix class cast exception on actualResponse
    @Test
    fun `addMaintenance() should add a new completed maintenance record`() {
        // given
        val expectedResponse = completedMaintenanceEntity

        //when
        every {
            completedMaintenanceService.createCompletedMaintenance(
                vehicle.vehicleId,
                completedMaintenance
            )
        } returns expectedResponse
        val actualResponse = completedMaintenanceController.addMaintenance(vehicle.vehicleId, completedMaintenance)

        //then
        verify(exactly = 1) {
            completedMaintenanceService.createCompletedMaintenance(
                vehicle.vehicleId,
                completedMaintenance
            )
        }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `deleteCompletedMaintenance() success`() {
        //when
        completedMaintenanceController.deleteCompletedMaintenance(completedMaintenanceEntity.completedMaintenanceId)

        //then
        verify(exactly = 1) { completedMaintenanceService.removeCompletedMaintenance(completedMaintenanceEntity.completedMaintenanceId) }
        confirmVerified()
    }

    @Test
    fun `updateCompletedMaintenanceName() success`() {
        //given
        val expectedCompletedMaintenanceResponse = CompletedMaintenance(
            name = "Updated Oil Change",
            notes = null,
            date = "4/32/2023",
            mileage = 102000,
            mechanics = "Self",
            serviceCenter = null,
            totalCost = 63.5,
        )

        //when
        every {
            completedMaintenanceController.updateCompletedMaintenance(
                completedMaintenanceEntity.completedMaintenanceId,
                expectedCompletedMaintenanceResponse
            )
        } returns Unit
        val response = completedMaintenanceController.updateCompletedMaintenance(
            completedMaintenanceEntity.completedMaintenanceId,
            expectedCompletedMaintenanceResponse
        )

        //then
        verify(exactly = 1) {
            completedMaintenanceService.updateCompletedMaintenance(
                completedMaintenanceEntity.completedMaintenanceId,
                expectedCompletedMaintenanceResponse
            )
        }
        assertEquals(response, Unit)
        confirmVerified()
    }
}