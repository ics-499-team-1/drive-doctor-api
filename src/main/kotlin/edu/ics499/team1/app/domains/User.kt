package edu.ics499.team1.app.domains

import edu.ics499.team1.app.entities.UserEntity
import org.springframework.security.crypto.password.AbstractPasswordEncoder

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String?
) {
    fun toUserEntity(password: String) = UserEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = password,
        phoneNumber = this.phoneNumber
    )
}
