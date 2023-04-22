package edu.ics499.team1.app.security.auth

data class AuthenticationResponse(
    val accessToken: String,
    val userId: Int
)
