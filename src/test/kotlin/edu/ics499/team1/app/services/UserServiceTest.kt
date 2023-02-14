package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositories.UserRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class UserServiceTest {
    private val dataSource: UserRepository = mockk(relaxed = true)
    private val userService = UserService(dataSource)
    @Test
    fun `should call its data source to retrieve users`() {
        // when
        userService.getUsers()
        
        // then
        verify(exactly = 1) { dataSource.findAll() }
    }
}