package edu.ics499.team1.app.repositiories

import edu.ics499.team1.app.domains.User

interface UserDataSource {
    fun retrieveUsers(): Collection<User>
}
