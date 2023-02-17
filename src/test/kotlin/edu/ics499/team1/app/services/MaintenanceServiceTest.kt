//package edu.ics499.team1.app.services
//
//import edu.ics499.team1.app.repositories.MaintenanceRepository
//import io.mockk.mockk
//import io.mockk.verify
//import org.junit.jupiter.api.Test
//
//internal class MaintenanceServiceTest {
//    private val dataSource : MaintenanceRepository = mockk(relaxed = true)
//    private val maintenanceService = MaintenanceService(dataSource)
//
//    @Test
//    fun `should call its data source to retrieve upcoming maintenance list`() {
//        // when
//        maintenanceService.getUpcomingMaintenance()
//
//        // then
//        verify (exactly = 1) { dataSource.findAll() }
//    }
//}