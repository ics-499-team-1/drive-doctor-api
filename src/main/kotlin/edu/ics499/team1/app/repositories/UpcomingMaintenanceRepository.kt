package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UpcomingMaintenanceRepository : JpaRepository<UpcomingMaintenanceEntity, Int>
