package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.services.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

class UserControllerTest {

    private val userService: UserService = mockk(relaxed = true)
    private val userController = UserController(userService)

    @Test
    fun `getUser() successful response`() {
        // given
        val userId = 123
        val userEntity = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())

        // when
        userController.getUser(userId)

        // then
        every { userService.getUser(123) } returns Optional.of(userEntity)
        verify(exactly = 1) { userService.getUser(userId) }
    }
}
