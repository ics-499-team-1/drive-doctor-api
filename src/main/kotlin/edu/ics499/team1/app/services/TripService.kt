package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.repositiories.TripDataSource
import org.springframework.stereotype.Service

@Service
class TripService(private val dataSource: TripDataSource) {
    fun getTrips(): Collection<Trip> = dataSource.retrieveTrips()
}
