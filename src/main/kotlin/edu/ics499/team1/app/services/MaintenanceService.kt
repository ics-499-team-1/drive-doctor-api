package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.UpcomingMaintenance
import edu.ics499.team1.app.repositiories.MaintenanceDataSource
import org.springframework.stereotype.Service

@Service
class MaintenanceService(private val dataSource: MaintenanceDataSource) {
    fun getUpcomingMaintenance() : Collection<UpcomingMaintenance> = dataSource.retrieveUpcomingMaintenance()
}
