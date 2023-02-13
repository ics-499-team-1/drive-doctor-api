package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositiories.VehicleDataSource
import io.mockk.mockk

internal class UserServiceTest {
    private val dataSource: VehicleDataSource = mockk(relaxed = true)

    private val vehicleService = VehicleService(dataSource)
}