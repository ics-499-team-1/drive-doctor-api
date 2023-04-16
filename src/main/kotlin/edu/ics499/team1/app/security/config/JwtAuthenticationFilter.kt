package edu.ics499.team1.app.security.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import reactor.util.annotation.NonNull

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    val jwtService: JwtService = JwtService()

    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        // Try to extract the authorization header
        val authHeader: String = request.getHeader("Authorization")
        val jwtToken: String
        val userEmail: String

        /* If the authorization header is null OR not the authorization header starts with Bearer
        Then pass the request and response to the next filter. */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the jwt token from the authorization header ("Bearer " = 7 spaces)
        jwtToken= authHeader.substring(7)
        // Extract the user email
        userEmail = jwtService.extractUsername(jwtToken)
    }

}