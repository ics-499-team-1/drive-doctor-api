package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val dataSource: UserRepository) {
    fun getUsers(): Collection<User> = listOf(User(
        1,
        "alex",
        "alex@mail.com",
        null,
        emptyList(),
        emptyList()
    ))
}
