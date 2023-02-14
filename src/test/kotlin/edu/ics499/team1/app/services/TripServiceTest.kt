package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositories.TripRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class TripServiceTest {
    private val dataSource : TripRepository = mockk(relaxed = true)
    private val tripService = TripService(dataSource)

    @Test
    fun `should call its data source to retrieve trips`() {
        //when
        tripService.getTrips()

        // then
        verify(exactly = 1) { dataSource.findAll() }
    }

}