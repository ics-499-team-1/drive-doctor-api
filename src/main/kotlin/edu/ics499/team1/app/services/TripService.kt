package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.repositories.TripRepository
import org.springframework.stereotype.Service

@Service
class TripService(private val dataSource: TripRepository) {
    fun getTrips(): Collection<Trip> = dataSource.findAll()
}
