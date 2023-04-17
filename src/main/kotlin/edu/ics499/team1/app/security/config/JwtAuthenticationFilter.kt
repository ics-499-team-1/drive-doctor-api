package edu.ics499.team1.app.security.config

import io.jsonwebtoken.Jwt
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import reactor.util.annotation.NonNull
import java.io.IOException

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        if (request.servletPath.contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Try to extract the authorization header
        val authHeader: String? = request.getHeader("Authorization")
        val userEmail: String?

        /* If the authorization header is null OR not the authorization header starts with Bearer
        Then pass the request and response to the next filter. */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the jwt token from the authorization header ("Bearer " = 7 spaces)
        val jwtToken: String = authHeader.substring(7)

        // Extract the user email
        userEmail = jwtService.extractUsername(jwtToken)

        // If the user's email is not null and the user isn't already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().authentication == null) {
            // Get the user details from the database
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(userEmail)
            // If the user and jwt token is valid
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // Create a username and password authentication token utilizing the user details and authorities
                val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                // Enforce the authentication token with the request
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                // update the authentication token
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        /* Causes the next filter in the chain to be invoked, or if the calling filter is the last filter in the chain,
           causes the resource at the end of the chain to be invoked. */
        filterChain.doFilter(request, response)
    }
}