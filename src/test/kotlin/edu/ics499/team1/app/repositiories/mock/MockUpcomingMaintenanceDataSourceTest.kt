package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockMaintenanceDataSourceTest {
    private val mockDataSource = MockMaintenanceDataSource()
    @Test
    fun `should retrieve a collection of maintenance items`() {
        // when
        val maintenance = mockDataSource.retrieveMaintenance()

        // then
        Assertions.assertThat(maintenance).isNotEmpty
    }
}