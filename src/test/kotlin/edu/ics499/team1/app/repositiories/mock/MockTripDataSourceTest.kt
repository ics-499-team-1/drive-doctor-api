package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MockTripDataSourceTest {
    private val mockDataSource = MockTripDataSource()

    @Test
    fun `should a collection of trips`() {
       // when
        val trips = mockDataSource.retrieveTrips()
        
        // then
        assertThat(trips).isNotEmpty
    }
    
    @Test
    fun `should provide some mock data`() {
        // when
        val trips = mockDataSource.retrieveTrips()
        
        // then
        assertThat(trips) {it.mileage >= 0}
        assertThat(trips) {it.type.isNotEmpty()}
        assertThat(trips) {it.vehicleId >= 0}
    }

    @Test
    fun `should have unique ids for all trips`() {
        // when
        val trips = mockDataSource.retrieveTrips()
        // If the sizes are equal, then no duplicates in the list of vehicles and test will pass.
        // If sizes aren't equal, then there are duplicates and the test will fail.
        val uniqueIds = trips.map {it.vehicleId}.toSet()

        // then
        Assertions.assertTrue(trips.size == uniqueIds.size, "All trips should have unique ids")
    }
}