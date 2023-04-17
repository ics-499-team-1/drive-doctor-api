package edu.ics499.team1.app.security.auth

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.entities.UserEntity
import edu.ics499.team1.app.repositories.UserRepository
import edu.ics499.team1.app.security.config.JwtService
import edu.ics499.team1.app.services.CustomExceptions
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
class AuthenticationService(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository
) {
    fun register(request: RegisterRequest): AuthenticationResponse {
        if (userRepository.existsByFirstNameAndLastNameAndEmail
                (request.firstName, request.lastName, request.email)
        ) {
            throw CustomExceptions.UserAlreadyExistsException("User already exists in the system")
        }
        val user = User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            phoneNumber = request.phoneNumber
        ).toUserEntity()
        userRepository.save(user)
        val jwtToken = jwtService.generateJwtToken(user)
        println(AuthenticationResponse(jwtToken))
        return AuthenticationResponse(jwtToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        val user = userRepository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateJwtToken(user)
        return AuthenticationResponse(jwtToken)
    }
}