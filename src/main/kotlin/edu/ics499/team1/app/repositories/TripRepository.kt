package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.TripEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TripRepository : JpaRepository<TripEntity, Int> {

}
