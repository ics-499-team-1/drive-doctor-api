package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.CompletedMaintenance
import org.springframework.stereotype.Repository

@Repository
interface CompletedMaintenanceRepository : DriveDoctorRepository<CompletedMaintenance, Long>{

}