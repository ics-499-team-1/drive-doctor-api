package edu.ics499.team1.app.services

import edu.ics499.team1.app.fixtures.CompletedMaintenanceFixture
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class CompletedMaintenanceServiceTest {
    private val cMRepository: CompletedMaintenanceRepository = mockk(relaxed = true)
    private val vehicleRepository: VehicleRepository = mockk(relaxed = true)
    private val cMService = CompletedMaintenanceService(cMRepository, vehicleRepository)

    @Test
    fun getCompletedMaintenanceByVehicleIdTest() {
        //given
        val cMID = 1
        val cMList = listOf(
            CompletedMaintenanceFixture.completedMaintenanceEntity(maintenanceId = 1),
            CompletedMaintenanceFixture.completedMaintenanceEntity(maintenanceId = 2)
        )
        val vehicleID = 123
        //when
        every { cMService.getCompletedMaintenanceByVehicleId(vehicleID) } returns cMList
        val expectedResponse = cMList
        val actualResponse = cMService.getCompletedMaintenanceByVehicleId(vehicleID)

        //then
        verify(exactly = 1) {
            cMService.getCompletedMaintenanceByVehicleId(vehicleID)
        }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()

    }


    // TODO FUBAR: Cast Class error
    @Test
    @Disabled
    fun createCompletedMaintenanceTest() {
        //given
        val cMDomain = CompletedMaintenanceFixture.completedMaintenanceDomain()
        val vehicleID = 123

        //when
        every { cMService.createCompletedMaintenance(vehicleID, cMDomain) } returns Unit
        val expectedResponse = Unit
        val actualResponse = cMService.createCompletedMaintenance(vehicleID, cMDomain)

        //then
        verify(exactly = 1) { cMService.createCompletedMaintenance(vehicleID, cMDomain) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun removeCompletedMaintenanceTest() {
        //given
        val cMID = 1

        //when
        every { cMService.removeCompletedMaintenance(cMID) } returns Unit
        val expectedResponse = Unit
        val actualResponse = cMService.removeCompletedMaintenance(cMID)

        //then
        verify(exactly = 1) { cMService.removeCompletedMaintenance(cMID) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun updateCompletedMaintenanceNameTest() {
        //given
        val cMID = 1
        val name = "Craig"

        //when
        every { cMService.updateCompletedMaintenanceName(cMID, name) } returns Unit
        val expectedResponse = Unit
        val actualResponse = cMService.updateCompletedMaintenanceName(cMID, name)

        //then
        verify(exactly = 1) { cMService.updateCompletedMaintenanceName(cMID, name) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }
}