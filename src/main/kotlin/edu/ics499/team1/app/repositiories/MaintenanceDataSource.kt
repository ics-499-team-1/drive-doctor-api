package edu.ics499.team1.app.repositiories

import edu.ics499.team1.app.domains.Maintenance

interface MaintenanceDataSource {
    fun retrieveMaintenance(): Collection<Maintenance>
}