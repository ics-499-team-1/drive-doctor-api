package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.security.auth.AuthenticationRequest
import edu.ics499.team1.app.security.auth.AuthenticationResponse
import edu.ics499.team1.app.security.jwt.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository
) {
    fun register(user: User): AuthenticationResponse {
        if (userRepository.existsByFirstNameAndLastNameAndEmail
                (user.firstName, user.lastName, user.email)
        ) {
            throw CustomExceptions.UserAlreadyExistsException("User already exists in the system")
        }
        val userEntity = user.toUserEntity()
        val createdUser = userRepository.save(userEntity)
        val jwtToken = jwtService.generateJwtToken(userEntity)
        return AuthenticationResponse(jwtToken, createdUser.userId)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        val user = userRepository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateJwtToken(user)
        return AuthenticationResponse(jwtToken, user.userId)
    }
}
