package edu.ics499.team1.app.repositories

import edu.ics499.team1.app.domains.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findByUserId(id: Integer): User?
    fun findByName(name: String): List<User>
    fun findByEmail(email: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}