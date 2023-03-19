package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositories.TripRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class TripServiceTest {
    private val tripDataSource: TripRepository = mockk(relaxed = true)
    private val vehicleDataSource: VehicleRepository = mockk(relaxed = true)
    private val tripService = TripService(tripDataSource, vehicleDataSource)

    @Test
    fun `should call its data source to retrieve trips`() {
        //when
        tripService.getTrips()

        // then
        verify(exactly = 1) { tripDataSource.findAll() }
        verify(exactly = 1) { vehicleDataSource.findAll() }
    }

}