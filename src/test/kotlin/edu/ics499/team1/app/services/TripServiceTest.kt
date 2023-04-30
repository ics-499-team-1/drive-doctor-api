package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.domains.VehicleTripMileage
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.TripRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TripServiceTest {

    private val tripRepository = mockk<TripRepository>()
    private val vehicleRepository = mockk<VehicleRepository>()
    private val userService = mockk<UserService>()

    private val mileage = 100

    private val tripService = TripService(
        tripRepository,
        vehicleRepository,
        userService
    )

    private val tripDomain = Trip(
        mileage = mileage,
        type = "Business",
        vehicleId = 1,
        name = null,
        start = null,
        end = null,
        notes = null,
    )

    private val user = UserEntity(
        userId = 1,
        firstName = "John",
        lastName = "Doe",
        email = "fake@email.com",
        phoneNumber = "123-456-789",
        vehicles = emptyList(),
        password = "password",
    )

    private val vehicleEntity = VehicleEntity(
        vehicleId = 1,
        name = "Truck",
        year = 2022,
        make = "Ford",
        model = "F150",
        trim = "XLT 302A",
        odometer = 3500,
        licensePlateNumber = "662 YUG",
        vin = "YD78F7GREG79524JG",
        deactivated = false,
        user = user,
    )

    private val tripEntity = tripDomain.toTripEntity(vehicleEntity)

    @Test
    fun `getTrips successful response - should call its data source to retrieve trips`() {
        // given
        val listOfTripEntity = listOf(tripEntity)

        //when
        every { tripRepository.findAll() } returns listOfTripEntity
        val actualResponse = tripService.getTrips()

        // then
        verify(exactly = 1) { tripRepository.findAll() }
        assertEquals(listOfTripEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `addTrip successful response`() {
        // given
        val tripEntity = tripDomain.toTripEntity(vehicleEntity)

        // when
        every { vehicleRepository.getReferenceById(tripDomain.vehicleId) } returns vehicleEntity
        every { tripRepository.save(tripDomain.toTripEntity(vehicleReference = vehicleEntity)) } returns tripEntity
        val actualResponse = tripService.addTrip(trip = tripDomain)

        // then
        verify(exactly = 1) { vehicleRepository.getReferenceById(tripDomain.vehicleId) }
        verify(exactly = 1) { tripRepository.save(tripDomain.toTripEntity(vehicleReference = vehicleEntity)) }
        assertEquals(tripEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `setMileage successful response`() {
        // given
        val tripEntity = tripDomain.toTripEntity(vehicleEntity)

        // when
        every { tripRepository.updateMileage(tripId = tripEntity.tripId, mileage = mileage) } returns Unit
        val actualResponse = tripService.setMileage(tripId = tripEntity.tripId, mileage = mileage)

        // then
        verify(exactly = 1) { tripRepository.updateMileage(tripId = tripEntity.tripId, mileage = mileage) }
        assertEquals(Unit, actualResponse)
        confirmVerified()
    }

    @Test
    fun `getTotalMileage successful response`() {
        // given
        val vehicleTripMileage = VehicleTripMileage(
            vehicleId = vehicleEntity.vehicleId,
            vehicleName = vehicleEntity.name,
            totalMiles = vehicleEntity.trips.sumOf { it.mileage },
            trips = vehicleEntity.trips
        )

        // when
        every { vehicleRepository.getReferenceById(vehicleEntity.vehicleId) } returns vehicleEntity
        every { tripRepository.findAllByVehicle(vehicleEntity) } returns vehicleEntity.trips
        val actualResponse = tripService.getTotalMileage(vehicleId = vehicleEntity.vehicleId)

        // then
        verify(exactly = 1) { vehicleRepository.getReferenceById(vehicleEntity.vehicleId) }
        verify(exactly = 1) { tripRepository.findAllByVehicle(vehicleEntity) }
        assertEquals(vehicleTripMileage, actualResponse)
        confirmVerified()
    }

    @Test
    fun `getTotalMileageByUser successful response`() {
        // given
        val vehicleTripMileageList = listOf<VehicleTripMileage>(VehicleTripMileage(
            vehicleId = vehicleEntity.vehicleId,
            vehicleName = vehicleEntity.name,
            totalMiles = vehicleEntity.trips.sumOf { it.mileage },
            trips = vehicleEntity.trips
        ))

        // when
        every { userService.getUserVehicles(user.userId) } returns listOf(vehicleEntity)
        every { tripRepository.findAllByVehicle(vehicleEntity) } returns vehicleEntity.trips
        val actualResponse = tripService.getTotalMileageByUser(userId = user.userId)

        // then
        verify(exactly = 1) { userService.getUserVehicles(user.userId) }
        verify(exactly = 1) { tripRepository.findAllByVehicle(vehicleEntity) }
        assertEquals(vehicleTripMileageList, actualResponse)
        confirmVerified()
    }
}
