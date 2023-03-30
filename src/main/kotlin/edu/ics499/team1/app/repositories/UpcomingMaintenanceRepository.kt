package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.UpcomingMaintenanceEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UpcomingMaintenanceRepository : JpaRepository<UpcomingMaintenanceEntity, Int> {

    /**
     * Updates the name field of the UpcomingMaintenanceEntity.
     * @param maintenanceID
     * @param name
     * @return Unit
     */
    @Transactional
    @Modifying
    @Query("update UpcomingMaintenanceEntity m set m.name =?2, m.notes=?3, m.pictures=?4, m.mileageInterval=?5, m.timeInterval=?6, m.mileageReminder=?7, m.timeReminder=?8 where m.upcomingMaintenanceId = ?1")
    fun modifyUpcomingMaintenanceName(
        maintenanceId: Int,
        name: String,
        notes: String?,
        pictures: String?,
        mileageInterval: Int?,
        timeInterval: String?,
        mileageReminder: Boolean?,
        timeReminder: Boolean
    )
}
