package edu.ics499.team1.app.repositiories

import edu.ics499.team1.app.domains.Vehicle

interface VehicleDataSource {

    fun retrieveVehicles(): Collection<Vehicle>
}