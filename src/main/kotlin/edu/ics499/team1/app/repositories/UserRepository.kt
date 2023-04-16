package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun existsByFirstNameAndLastNameAndEmail(firstName: String, lastName: String, email: String): Boolean

    fun findByEmail(email: String): Optional<UserEntity>
}
