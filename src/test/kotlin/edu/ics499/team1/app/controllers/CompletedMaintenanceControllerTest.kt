package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.fixtures.CompletedMaintenanceFixture
import edu.ics499.team1.app.services.CompletedMaintenanceService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CompletedMaintenanceControllerTest {

    val cMService: CompletedMaintenanceService = mockk(relaxed = true)
    val cMController = CompletedMaintenanceController(cMService)

    @Test
    fun getCompletedMaintenancesByVehicleIdTest() {
        //given
        val vehicleID = 1
        val cMList = listOf(
            CompletedMaintenanceFixture.completedMaintenanceEntity(maintenanceId = 1),
            CompletedMaintenanceFixture.completedMaintenanceEntity(maintenanceId = 2)
        )

        //when
        every { cMService.getCompletedMaintenanceByVehicleId(vehicleID) } returns cMList
        val expectedResponse = cMList
        val actualResponse = cMService.getCompletedMaintenanceByVehicleId(vehicleID)

        //then
        verify(exactly = 1) { cMService.getCompletedMaintenanceByVehicleId(vehicleID) }
        assertEquals(actualResponse, expectedResponse)
        confirmVerified()
    }

    @Test
    fun addMaintenanceTest() {
        //given
        val vehicleID = 123456
        val cMDomain = CompletedMaintenanceFixture.completedMaintenanceDomain()

        //when
        every { cMController.addMaintenance(vehicleID, cMDomain) } returns Unit
        val expectedValue = Unit
        val actualValue = cMController.addMaintenance(vehicleID, cMDomain)

        //then
        verify(exactly = 1) { cMController.addMaintenance(vehicleID, cMDomain) }
        assertEquals(expectedValue, actualValue)
        confirmVerified()
    }

    @Test
    fun deleteCompletedMaintenanceTest() {
        //given
        val cMID = 1234

        //when
        every { cMController.deleteCompletedMaintenance(cMID) } returns Unit
        val expectedValue = Unit
        val actualValue = cMController.deleteCompletedMaintenance(cMID)

        //then
        verify(exactly = 1) { cMController.deleteCompletedMaintenance(cMID) }
        assertEquals(expectedValue, actualValue)
        confirmVerified()
    }

    @Test
    fun updateCompletedMaintenanceNameTest() {
        //given
        val cMID = 321
        val cMEntity = CompletedMaintenanceFixture.completedMaintenanceDomain(name = "Henry")

        //when
        every { cMController.updateCompletedMaintenanceName(cMID, cMEntity) } returns Unit
        val expectedValue = Unit
        val actualValue = cMController.updateCompletedMaintenanceName(cMID, cMEntity)

        //then
        verify(exactly = 1) { cMController.updateCompletedMaintenanceName(cMID, cMEntity) }
        assertEquals(expectedValue, actualValue)
        confirmVerified()
    }
}