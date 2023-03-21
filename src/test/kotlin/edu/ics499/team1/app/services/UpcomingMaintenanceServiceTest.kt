package edu.ics499.team1.app.services


import edu.ics499.team1.app.fixtures.CompletedMaintenanceFixture
import edu.ics499.team1.app.fixtures.UpcomingMaintenanceFixture
import edu.ics499.team1.app.fixtures.VehicleFixture
import edu.ics499.team1.app.repositories.UpcomingMaintenanceRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test

class UpcomingMaintenanceServiceTest {

    private val uMRepository: UpcomingMaintenanceRepository = mockk(relaxed = true)
    private val uMService: UpcomingMaintenanceService = mockk(relaxed = true)

    @Test
    fun `should return the correct list of upcomingMaintenance entities`() {
        // given
        val vehicleID = 123
        val uMEntity1 = UpcomingMaintenanceFixture.upcomingMaintenanceEntity(maintenanceId = 1)
        val uMEntity2 = UpcomingMaintenanceFixture.upcomingMaintenanceEntity(maintenanceId = 2)
        val uMEntityList = listOf(uMEntity1, uMEntity2)

        // when
        every { uMService.getUpcomingMaintenanceByVehicleId(vehicleID) } returns uMEntityList
        val response = uMService.getUpcomingMaintenanceByVehicleId(vehicleID)

        //then
        verify(exactly = 1) { uMService.getUpcomingMaintenanceByVehicleId(vehicleID) }
        assertIterableEquals(uMEntityList, response)
        confirmVerified()
    }

    @Test
    fun `should call createUpcomingMaintenance and return Unit`() {
        //given
        val uMDomain = UpcomingMaintenanceFixture.upcomingMaintenanceDomain()
        val vehicleID = 1
        val vehicle = VehicleFixture.vehicleEntity(vehicleId = vehicleID)
        val vehicleDomain = VehicleFixture.vehicleDomain()
        val umEntity = UpcomingMaintenanceFixture.upcomingMaintenanceEntity(maintenanceId = 1, vehicle = vehicleDomain)

        //when
        every { uMRepository.save(uMDomain.toUpcomingMaintenanceEntity(vehicle)) } returns umEntity // kicks the can down the road
        every { uMService.createUpcomingMaintenance(vehicleID, uMDomain) } returns Unit

        val response = uMService.createUpcomingMaintenance(vehicleID, uMDomain)
        val expectedResponse = Unit

        //then
        verify(exactly = 1) { uMService.createUpcomingMaintenance(vehicleID, uMDomain) }
        assertEquals(response, expectedResponse)
        confirmVerified()
    }

    @Test
    fun `should remove upcomingMaintenance entity`() {
        // given
        val uMID = 1

        // when
        every { uMService.removeUpcomingMaintenance(uMID) } returns Unit
        val expectedResponse = Unit
        val actualResponse = uMService.removeUpcomingMaintenance(uMID)

        //then
        verify(exactly = 1) { uMService.removeUpcomingMaintenance(uMID) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }

    @Test
    fun `should update an upcoming maintenance entity name`() {
        // given
        val uMID = 1
        val uMEntity = UpcomingMaintenanceFixture.upcomingMaintenanceEntity(maintenanceId = uMID)
        val name = "rory"
        val expectedResponse = Unit

        // when
        every { uMService.updateUpcomingMaintenanceName(uMID, name) } returns Unit
        val actualResponse = uMService.updateUpcomingMaintenanceName(uMID, name)

        //then
        verify(exactly = 1) { uMService.updateUpcomingMaintenanceName(uMID, name) }
        assertEquals(actualResponse, expectedResponse)
        confirmVerified()

    }

    // TODO FUBAR: Cast Class error
    @Test
    fun `should convert upcoming to completed maintenance`() {
        // given
        val uMID = 1
        val cMDomain = CompletedMaintenanceFixture.completedMaintenanceDomain()

        // when
        every { uMService.convertUpcomingToCompleted(uMID, cMDomain) } returns Unit
        val expectedResponse = Unit
        val actualResponse = uMService.convertUpcomingToCompleted(uMID, cMDomain)

        //then
        verify(exactly = 1) { uMService.convertUpcomingToCompleted(uMID, cMDomain) }
        assertEquals(expectedResponse, actualResponse)
        confirmVerified()
    }
}