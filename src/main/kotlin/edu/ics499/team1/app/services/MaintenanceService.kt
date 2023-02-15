package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Maintenance
import edu.ics499.team1.app.repositories.MaintenanceRepository
import org.springframework.stereotype.Service

@Service
class MaintenanceService(private val dataSource: MaintenanceRepository) {
    fun getUpcomingMaintenance(): MutableList<Maintenance> = dataSource.findAll()
}
