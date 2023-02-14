package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.Maintenance
import org.springframework.stereotype.Repository

@Repository
interface MaintenanceRepository : DriveDoctorRepository<Maintenance, Long>{

}