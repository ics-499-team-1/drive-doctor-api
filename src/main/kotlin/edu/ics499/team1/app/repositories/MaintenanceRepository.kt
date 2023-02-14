package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.Maintenance
import org.springframework.data.jpa.repository.JpaRepository

interface MaintenanceRepository : JpaRepository<Maintenance, Long>