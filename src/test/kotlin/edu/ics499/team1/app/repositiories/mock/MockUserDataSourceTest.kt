package edu.ics499.team1.app.repositiories.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockUserDataSourceTest {
    private val mockDataSource = MockUserDataSource()
    @Test
    fun `should provide a collection of users`() {

        // when
        val users = mockDataSource.retrieveUsers()
        
        // then
        assertThat(users).isNotEmpty
    }
    
    @Test
    fun `should provide some mock data`() {
        // when
        val users = mockDataSource.retrieveUsers()
        
        // then
        assertThat(users).allMatch { it.userId >= 0 }
        assertThat(users).allMatch { it.name.isNotEmpty() }
        assertThat(users).allMatch { it.email.isNotEmpty() }
        assertThat(users).allMatch { it.phoneNumber.isNotEmpty() }
    }
}