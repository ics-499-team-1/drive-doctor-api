package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockUpcomingMaintenanceDataSourceTest {
    private val mockDataSource = MockUpcomingMaintenanceDataSource()
    @Test
    fun `should retrieve a collection of maintenance items`() {
        // when
        val maintenance = mockDataSource.retrieveUpcomingMaintenance()

        // then
        assertThat(maintenance).isNotEmpty
    }

    @Test
    fun `should provide a collection with mock data`() {
        // given


        // when
        val maintenance = mockDataSource.retrieveUpcomingMaintenance()

        // then
        assertThat(maintenance).allMatch {it.name.isNotEmpty()}
        assertThat(maintenance).allMatch {it.notes.isNotEmpty()}
        assertThat(maintenance).allMatch {it.pictures.isNotEmpty()} // pictures should probably be optional
        assertThat(maintenance).allMatch {it.mileageInterval >= 0}
        assertThat(maintenance).allMatch {it.timeInterval.isNotEmpty()}
    }
}