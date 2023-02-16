package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CompletedMaintenanceRepository : JpaRepository<CompletedMaintenanceEntity, Int> {
}