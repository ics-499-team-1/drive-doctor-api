package edu.ics499.team1.app.fixtures

import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.entities.VehicleEntity
import kotlin.random.Random

class UserFixture {

    companion object {
        fun user(
            userId: Int = Random.nextInt(),
            firstName: String = "first$userId",
            lastName: String = "last$userId",
            email: String = "email$userId",
            password: String = "password$userId",
            phoneNumber: String? = null,
            vehicles: List<VehicleEntity> = listOf()
        ): UserEntity {
            return UserEntity(
                userId = userId,
                firstName = firstName,
                lastName = lastName,
                email = email,
                phoneNumber = phoneNumber,
                vehicles = vehicles,
                password = password
            )
        }
    }
}