package edu.ics499.team1.app.repositories.mock

import edu.ics499.team1.app.domains.requests.Vehicle

class MockVehicleDataSource {
    val vehicles = mutableListOf(
        Vehicle(0, "Vehicle0", 2020, "", "", 0, "", "", emptyList(), emptyList(), true),
        Vehicle(
            1,
            "Vehicle1",
            2019,
            "Toyota",
            "Camry SE",
            15000,
            "ABC123",
            "1NXBR32E79Z",
            emptyList(),
            emptyList(),
            false
        ),
        Vehicle(
            2,
            "Vehicle2",
            2021,
            "Honda",
            "Civic LX",
            5000,
            "DEF456",
            "SHHFK7H41LU",
            emptyList(),
            emptyList(),
            false
        ),
        Vehicle(
            3,
            "Vehicle3",
            2020,
            "Ford",
            "Mustang GT",
            8000,
            "GHI789",
            "1ZVBP8CF2E5278042",
            emptyList(),
            emptyList(),
            false
        ),
        Vehicle(
            4,
            "Vehicle4",
            2018,
            "Chevrolet",
            "Silverado 1500",
            20000,
            "JKL101",
            "3GCUKREC2JG176855",
            emptyList(),
            emptyList(),
            false
        ),
        Vehicle(
            5,
            "Vehicle5",
            2017,
            "Jeep",
            "Wrangler Rubicon",
            12000,
            "MNO202",
            "1C4BJWFG6HL743501",
            emptyList(),
            emptyList(),
            false
        ),
    )

    fun retrieveVehicles(): Collection<Vehicle> = vehicles
}
