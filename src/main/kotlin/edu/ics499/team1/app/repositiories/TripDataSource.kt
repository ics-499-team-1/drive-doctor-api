package edu.ics499.team1.app.repositiories

import edu.ics499.team1.app.domains.Trip

interface TripDataSource {

    fun retrieveTrips(): Collection<Trip>
}