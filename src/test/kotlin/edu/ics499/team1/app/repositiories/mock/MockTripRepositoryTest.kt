package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MockTripRepositoryTest {
    private val mockDataSource = MockTripRepository()

    @Test
    fun `should a collection of trips`() {
       // when
        val trips = mockDataSource.retrieveTrips()
        
        // then
        assertThat(trips).isNotEmpty
    }
    
    @Test
    fun `should provide some mock trip data`() {
        // when
        val trips = mockDataSource.retrieveTrips()
        
        // then
        assertThat(trips).allMatch {it.mileage >= 0}
        assertThat(trips).allMatch {it.type.isNotEmpty()}
        assertThat(trips).allMatch {it.vehicleId >= 0}
    }

    @Test
    fun `should have unique ids for all trips`() {
        // when
        val trips = mockDataSource.retrieveTrips()
        val vehicleIds = trips.map { it.vehicleId }
        val uniqueVehicleIds = vehicleIds.toSet()

        // then
        // If the sizes are equal, then no duplicates in the list of vehicles and test will pass.
        // If sizes aren't equal, then there are duplicates and the test will fail.
        Assertions.assertTrue(trips.size == uniqueVehicleIds.size, "All trips should have unique ids")
    }
}