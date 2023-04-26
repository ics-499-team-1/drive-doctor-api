package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.security.auth.AuthenticationRequest
import edu.ics499.team1.app.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody user: User) = authenticationService.register(user)

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest) = authenticationService.authenticate(request)
}
