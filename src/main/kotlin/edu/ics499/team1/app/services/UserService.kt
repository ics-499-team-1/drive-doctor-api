package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.repositiories.UserDataSource
import org.springframework.stereotype.Service

@Service
class UserService(private val dataSource: UserDataSource) {
    fun getUsers(): Collection<User> = dataSource.retrieveUsers()
}