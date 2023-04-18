package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.domains.VehicleTripMileage
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.services.TripService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TripControllerTest {
    private val mileage = 100

    private val tripService = mockk<TripService>()

    private val tripController = TripController(
        tripService
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
    fun `getTrips successful response`() {
        // given
        val listOfTripEntity = listOf(tripEntity)

        //when
        every { tripService.getTrips() } returns listOfTripEntity
        val actualResponse = tripController.getTrips()

        // then
        verify(exactly = 1) { tripService.getTrips() }
        assertEquals(listOfTripEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `addTrip successful response`() {
        // when
        every { tripService.addTrip(trip = tripDomain) } returns tripEntity
        val actualResponse = tripController.addTrip(tripDomain)

        // then
        verify(exactly = 1) { tripService.addTrip(trip = tripDomain) }
        assertEquals(tripEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `setMileage successful response`() {
        // when
        every { tripService.setMileage(tripId = tripEntity.tripId, mileage = mileage) } returns Unit
        val actualResponse = tripController.setMileage(tripId = tripEntity.tripId, mileage = mileage)

        // then
        verify(exactly = 1) { tripService.setMileage(tripId = tripEntity.tripId, mileage = mileage) }
        assertEquals(Unit, actualResponse)
        confirmVerified()
    }

    @Test
    fun `getTotalMileage successful response`() {
        // given
        val vehicleTripMileage = VehicleTripMileage(
            totalMiles = vehicleEntity.trips.sumOf { it.mileage },
            trips = vehicleEntity.trips
        )

        // when
        every { tripService.getTotalMileage(vehicleId = vehicleEntity.vehicleId) } returns vehicleTripMileage
        val actualResponse = tripController.getTotalMileage(vehicleId = vehicleEntity.vehicleId)

        // then
        verify(exactly = 1) { tripService.getTotalMileage(vehicleId = vehicleEntity.vehicleId) }
        assertEquals(vehicleTripMileage, actualResponse)
        confirmVerified()
    }
}