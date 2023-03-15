package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.services.CompletedMaintenanceService
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class CompletedMaintenanceControllerTest {
    private val completedMaintenanceService: CompletedMaintenanceService = mockk(relaxed = true)

    @Test
    fun `getCompletedMaintenanceByVehicleId() working as intended`(){

    }
}