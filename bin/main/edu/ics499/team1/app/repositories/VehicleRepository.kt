package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository : JpaRepository<VehicleEntity, Int>
