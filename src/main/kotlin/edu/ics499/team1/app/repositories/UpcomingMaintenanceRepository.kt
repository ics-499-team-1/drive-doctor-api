package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.UpcomingMaintenance
import org.springframework.stereotype.Repository

@Repository
interface UpcomingMaintenanceRepository : DriveDoctorRepository<UpcomingMaintenance, Long>{

}