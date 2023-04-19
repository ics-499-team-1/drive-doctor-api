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
    @Query("update CompletedMaintenanceEntity m set m.name =?2, m.notes=?3, m.date=?4, m.mileage=?5, m.serviceCenter=?6, m.mechanics=?7, m.totalCost=?8 where m.completedMaintenanceId = ?1")
    fun modifyCompletedMaintenanceName(
        maintenanceId: Int,
        name: String,
        notes: String?,
        date: String,
        mileage: Int,
        serviceCenter: String?,
        mechanics: String?,
        totalCost: Double?
    ): Unit
}
