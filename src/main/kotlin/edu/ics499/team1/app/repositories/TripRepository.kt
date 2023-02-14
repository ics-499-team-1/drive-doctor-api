package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.Trip
import org.springframework.stereotype.Repository

@Repository
interface TripRepository : DriveDoctorRepository<Trip, Long>{

}