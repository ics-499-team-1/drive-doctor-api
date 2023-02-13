package edu.ics499.team1.app.repositiories.mock

import edu.ics499.team1.app.domains.Maintenance
import edu.ics499.team1.app.repositiories.MaintenanceDataSource
import org.springframework.stereotype.Repository

@Repository
class MockMaintenanceDataSource : MaintenanceDataSource {
    private val maintenance = listOf("", "", "", 0, "", false, false)
    override fun retrieveMaintenance(): Collection<Maintenance> {
        return emptyList()
    }

}