package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.CompletedMaintenance
import edu.ics499.team1.app.domains.Vehicle
import edu.ics499.team1.app.entities.CompletedMaintenanceEntity
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.CompletedMaintenanceRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class CompletedMaintenanceServiceTest {
    private val completedMaintenanceRepository: CompletedMaintenanceRepository = mockk(relaxed = true)
    private val vehicleRepository: VehicleRepository = mockk(relaxed = true)

    private val completedMaintenanceService = CompletedMaintenanceService(completedMaintenanceRepository, vehicleRepository)

    @Test
    fun `getCompletedMaintenanceByVehicleId()`(){
//
//        //given
//        val user = UserEntity(
//            userId = 123,
//            firstName = "John",
//            lastName = "Smith",
//            email = "johnsmith@email.com",
//            phoneNumber = "651-555-4455"
//        )
//
//        val vehicle = Vehicle(
//            name = "My Car",
//            year = 2022,
//            make = "Toyota",
//            model = "Corolla",
//            trim = "LE",
//            odometer = 5000,
//            licensePlateNumber = "ABC123",
//            vin = "12345678901234567",
//            userId = user.userId,
//        )
//
//        val completedMaintenance = CompletedMaintenanceEntity(
//            maintenanceId = 11,
//            name = "Oil Change",
//            date ="3/12/2023",
//            mileage = 101000,
//            vehicle = vehicle.toVehicleEntity(user)
//        )
//        //when
//        val result = completedMaintenanceService.getCompletedMaintenanceByVehicleId(11)
//
//        //then
    }

    @Test
    fun `createCompletedMaintenance() working as expected`(){
        //given
        val user = UserEntity(
            userId = 123,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@email.com",
            phoneNumber = "651-555-4455"
        )

        val vehicle = Vehicle(
            name = "My Car",
            year = 2022,
            make = "Toyota",
            model = "Corolla",
            trim = "LE",
            odometer = 5000,
            licensePlateNumber = "ABC123",
            vin = "12345678901234567",
            userId = user.userId,
        )

        val completedMaintenance = CompletedMaintenance(
            name = "Oil Change",
            notes = null,
            date ="3/12/2023",
            mileage = 101000,
            mechanics = "Self",
            pictures = null,
            serviceCenter = null,
            totalCost = 63.5
        )
        //when
        val result = completedMaintenanceService.createCompletedMaintenance(1, completedMaintenance)

        //then

    }

    @Test
    fun `removeCompletedMaintenance()`(){

    }

    @Test
    fun `updateCompletedMaintenanceName()`(){

    }
}