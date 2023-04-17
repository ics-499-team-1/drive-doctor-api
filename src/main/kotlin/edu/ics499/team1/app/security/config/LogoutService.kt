package edu.ics499.team1.app.security.config

import com.alibou.security.token.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service


@Service
class LogoutService(
    private val tokenRepository: TokenRepository
) : LogoutHandler {

    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt: String = authHeader.substring(7)
        val storedToken: Unit = tokenRepository.findByToken(jwt)
            .orElse(null)
        if (storedToken != null) {
            storedToken.setExpired(true)
            storedToken.setRevoked(true)
            tokenRepository.save(storedToken)
            SecurityContextHolder.clearContext()
        }
    }
}