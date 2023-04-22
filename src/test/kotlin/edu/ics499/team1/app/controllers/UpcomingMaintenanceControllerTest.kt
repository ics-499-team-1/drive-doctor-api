package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.fixtures.CompletedMaintenanceFixture
import edu.ics499.team1.app.fixtures.UpcomingMaintenanceFixture
import edu.ics499.team1.app.services.UpcomingMaintenanceService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UpcomingMaintenanceControllerTest {

    private val uMService: UpcomingMaintenanceService = mockk(relaxed = true)
    private val uMController = UpcomingMaintenanceController(uMService)

    @Test
    fun `getUpcomingMaintenanceByVehicleID() 200 response`() {
        // given
        val vehicleId = 123
        val umEntity = listOf(
            UpcomingMaintenanceFixture.upcomingMaintenanceEntity(),
            UpcomingMaintenanceFixture.upcomingMaintenanceEntity()
        )

        // when
        every { uMService.getUpcomingMaintenanceByVehicleId(vehicleId) } returns umEntity
        val actualResponse = uMController.getUpcomingMaintenancesByVehicleId(vehicleId)

        // then
        verify(exactly = 1) { uMService.getUpcomingMaintenanceByVehicleId(vehicleId) }
        assertEquals(umEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `deleteUpcomingMaintenanceByID()`() {
        // given
        val uMId = 123

        // when
        every { uMService.removeUpcomingMaintenance(uMId) } returns Unit
        val actualResponse = uMController.deleteUpcomingMaintenance(uMId)

        //then
        verify(exactly = 1) { uMService.removeUpcomingMaintenance(uMId) }
        assertEquals(Unit, actualResponse)
        confirmVerified()
    }

    @Test
    fun `should add an UpcomingMaintenance entity to the repository`() {
        // given
        val vId = 1
        val uMDomain = UpcomingMaintenanceFixture.upcomingMaintenanceDomain()

        // when
        every { uMService.createUpcomingMaintenance(vId, uMDomain) } returns Unit
        val response = uMController.addMaintenance(vId, uMDomain)

        //then
        verify(exactly = 1) { uMService.createUpcomingMaintenance(vId, uMDomain) }
        assertEquals(response, Unit)
        confirmVerified()
    }

    @Test
    fun `should update the upcomingMaintenance`() {
        // given
        val uMId = 123
        val expectedResponse = UpcomingMaintenance(
            name = "",
            notes = null,
            mileageInterval = 1,
            timeInterval = "",
            mileageReminder = false,
            timeReminder = false
        )

        // when
        every { uMService.updateUpcomingMaintenanceEntity(uMId, expectedResponse) } returns Unit
        val response = uMController.updateUpcomingMaintenance(uMId, expectedResponse)

        //then
        verify(exactly = 1) { uMService.updateUpcomingMaintenanceEntity(uMId, expectedResponse) }
        assertEquals(response, Unit)
        confirmVerified()
    }

    @Test
    fun `should convert upcoming maintenance item to completed maintenance`() {
        // given
        val uMID = 123

        val cMDomain = CompletedMaintenanceFixture.completedMaintenanceDomain()

        // when
        every { uMService.convertUpcomingToCompleted(uMID, cMDomain) } returns Unit
        val response = uMService.convertUpcomingToCompleted(uMID, cMDomain)

        //then
        verify(exactly = 1) { uMService.convertUpcomingToCompleted(uMID, cMDomain) }
        assertEquals(Unit, response)
        confirmVerified()
    }

}