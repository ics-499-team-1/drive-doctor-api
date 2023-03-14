package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.Trip
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import edu.ics499.team1.app.repositories.TripRepository
import edu.ics499.team1.app.repositories.VehicleRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class TripServiceTest {

    private val tripRepository = mockk<TripRepository>()

    private val vehicleRepository = mockk<VehicleRepository>()

    private val tripService = TripService(
        tripRepository,
        vehicleRepository,
    )

    private val tripDomain = Trip(
        mileage = 50,
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
    )

    private val vehicle = VehicleEntity(
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

    private val tripEntity = tripDomain.toTripEntity(vehicle)

    @Test
    fun `addTrip successful response`() {
        // given
        val tripEntity = tripDomain.toTripEntity(vehicle)
        
        // when
        every { vehicleRepository.getReferenceById(tripDomain.vehicleId) } returns vehicle
        every { tripRepository.save(tripDomain.toTripEntity(vehicle)) } returns tripEntity
        val actualResponse = tripService.addTrip(tripDomain)
        
        // then
        verify(exactly = 1) { vehicleRepository.getReferenceById(tripDomain.vehicleId) }
        verify(exactly = 1) { tripRepository.save(tripDomain.toTripEntity(vehicle)) }
        assertEquals(tripEntity, actualResponse)
        confirmVerified()
    }

    @Test
    fun `getTotalMileage successful response`() {
        // given


        // when


        // then

    }
        
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
}