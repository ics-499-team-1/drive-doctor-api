package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MockVehicleDataSourceTest {
    private val mockDataSource = MockVehicleDataSource()
    @Test
    fun `should provide a collection of vehicles`() {
        // when
        val vehicles = mockDataSource.retrieveVehicles()

        // then
        assertThat(vehicles).hasSizeGreaterThanOrEqualTo(3)
    }
    
    @Test
    fun `should provide some mock data`() {
        // when
        val vehicles = mockDataSource.retrieveVehicles()

        // then
        assertThat(vehicles).allMatch { it.name.isNotBlank() }
        assertThat(vehicles).allMatch { it.id >= 0.0.toLong() }
        assertThat(vehicles).allMatch { it.year > 0}

    }

    @Test
    fun `should have unique ids for all vehicles`() {

        // when
        val vehicles = mockDataSource.retrieveVehicles()
        val uniqueIds = vehicles.map {it.id}.toSet()

        // then
        // If the sizes are equal, then no duplicates in the list of vehicles and test will pass.
        // If sizes aren't equal, then there are duplicates and the test will fail.
        assertTrue(vehicles.size == uniqueIds.size, "All vehicles should have unique ids")
    }
}