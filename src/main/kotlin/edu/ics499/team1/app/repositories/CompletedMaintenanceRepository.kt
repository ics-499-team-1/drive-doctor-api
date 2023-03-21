package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CompletedMaintenanceRepository : JpaRepository<CompletedMaintenanceEntity, Int> {

    /**
     * Updates the name field of the CompletedMaintenanceEntity.
     * @param maintenanceID
     * @param name
     * @return Unit
     */
    @Transactional
    @Modifying
    @Query("update CompletedMaintenanceEntity m set m.name =?2 where m.maintenanceId = ?1")
    fun modifyCompletedMaintenanceName(maintenanceId: Int, name: String): String
}
