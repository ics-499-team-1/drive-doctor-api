package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class VehicleServiceTest {

    // relaxed set to true means that whenever a method is called on it, it will return some default value
    private val dataSource: VehicleRepository = mockk(relaxed = true)

    private val vehicleService = VehicleService(dataSource)

    @Test
    fun `should call its data source to retrieve vehicles`() {
        // when
        vehicleService.getVehicles()

        // then
        verify(exactly = 1) { dataSource.findAll() }
    }
}
