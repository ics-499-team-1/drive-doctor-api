package edu.ics499.team1.app.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import edu.ics499.team1.app.security.user.Role
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue
    val userId: Int = 0,
    val firstName: String,
    val lastName: String,
    private val email: String,
    private val password: String,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,
    val phoneNumber: String?,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val vehicles: List<VehicleEntity> = emptyList(),
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
