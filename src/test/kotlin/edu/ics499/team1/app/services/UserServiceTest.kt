package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.UserRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class UserServiceTest {

    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserService(userRepository)

    @Test
    fun `createUser() called with successful response`() {
        // given
        val userRequest = User("John", "Doe", "johndoe@email.com", null)
        val expectedResponse = userRequest.toUserEntity()

        // when
        every { userRepository.save(userRequest.toUserEntity()) } returns expectedResponse
        val actualResponse = userService.createUser(userRequest)

        // then
        verify(exactly = 1) { userRepository.existsByFirstNameAndLastNameAndEmail(userRequest.firstName, userRequest.lastName, userRequest.email) }
        verify(exactly = 1) { userRepository.save(userRequest.toUserEntity()) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `getUser() is called with successful response`() {
        // given
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = Optional.of(user)

        // when
        every { userRepository.findById(user.userId) } returns expectedResponse
        val actualResponse = userService.getUser(user.userId)

        // then
        verify(exactly = 1) { userRepository.findById(user.userId) }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `should call its data source to retrieve users`() {
        // given
        val user = UserEntity(123, "John", "Doe", "johndoe@email.com", null, emptyList())
        val expectedResponse = listOf(user)

        // when
        every { userRepository.findAll() } returns expectedResponse
        val actualResponse = userService.getUsers()

        // then
        verify(exactly = 1) { userRepository.findAll() }
        assertEquals(expectedResponse, actualResponse)

        confirmVerified()
    }

    @Test
    fun `should call deleteUser() and a delete a user by their id`() {
        // given
        val userId = 123

    }
}