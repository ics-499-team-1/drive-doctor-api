package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.UserEntity

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String?
) {
    fun toUserEntity() = UserEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        phoneNumber = this.phoneNumber
    )
}
