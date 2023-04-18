package edu.ics499.team1.app.security.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
)
