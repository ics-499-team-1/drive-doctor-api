package edu.ics499.team1.app.repositiories

import edu.ics499.team1.app.domains.UpcomingMaintenance

interface MaintenanceDataSource {
    fun retrieveUpcomingMaintenance(): Collection<UpcomingMaintenance>
}