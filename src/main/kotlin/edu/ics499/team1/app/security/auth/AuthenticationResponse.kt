package edu.ics499.team1.app.security.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationResponse(
    private val token: String
)
