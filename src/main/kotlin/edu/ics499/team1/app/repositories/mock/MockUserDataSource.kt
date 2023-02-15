package edu.ics499.team1.app.repositories.mock

import edu.ics499.team1.app.domains.User

class MockUserDataSource {

    val users = mutableListOf(
        User(0, "Ryan", "rg@email.com", "123-456-7777", emptyList(), emptyList()),
        User(1, "John Doe", "john.doe@email.com", "123-456-7890", emptyList(), emptyList()),
        User(2, "Jane Doe", "jane.doe@email.com", "098-765-4321", emptyList(), emptyList()),
        User(3, "Bob Smith", "bob.smith@email.com", "111-222-3333", emptyList(), emptyList()),
        User(4, "Emma Wilson", "emma.wilson@email.com", "444-555-6666", emptyList(), emptyList()),
        User(5, "Michael Johnson", "michael.johnson@email.com", "777-888-9999", emptyList(), emptyList()),
    )

    fun retrieveUsers(): Collection<User> = users
}
