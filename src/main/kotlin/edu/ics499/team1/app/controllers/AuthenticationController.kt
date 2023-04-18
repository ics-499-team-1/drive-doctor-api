package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.security.auth.AuthenticationRequest
import edu.ics499.team1.app.security.auth.RegisterRequest
import edu.ics499.team1.app.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: RegisterRequest) = authenticationService.register(request)

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest) = authenticationService.authenticate(request)
}