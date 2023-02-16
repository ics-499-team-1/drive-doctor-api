package edu.ics499.team1.app.domains.requests

import edu.ics499.team1.app.entities.UserEntity

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?
) {
    fun toUserEntity() = UserEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        vehicles = emptyList()
    )
}
