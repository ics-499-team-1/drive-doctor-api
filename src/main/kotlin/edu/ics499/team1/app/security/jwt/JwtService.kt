package edu.ics499.team1.app.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtService {

    // Needs to be a minimum of 256-bit encryption in hex format
    @Value("\${drive-doctor.jwt-secret-key}")
    private final lateinit var secretKey: String

    fun extractUsername(jwtToken: String): String? {
        return extractClaim(jwtToken) { obj: Claims -> obj.subject }
    }

    fun <T> extractClaim(jwtToken: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(jwtToken)
        return claimsResolver(claims)
    }

    fun generateJwtToken(userDetails: UserDetails): String {
        return generateJwtToken(hashMapOf(), userDetails)
    }

    fun generateJwtToken(
        extractClaims: Map<String, Any>,
        userDetails: UserDetails
    ): String {
        return Jwts
            .builder() // returns a new JwtBuilder instance
            .setClaims(extractClaims) // set the extra claims
            .setSubject(userDetails.username) // set the subject of the claim
            .setIssuedAt(Date(System.currentTimeMillis())) // set the time of when the jwt token was issued
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8)) // set the time of when the jwt token will expire
            .signWith(
                getSignInKey(),
                SignatureAlgorithm.HS256
            ) // set the signing key and the algorithm that will be used
            .compact() // builds the JWT and serializes it to a compact URL-safe string
    }

    fun isTokenValid(
        jwtToken: String,
        userDetails: UserDetails
    ): Boolean {
        val username: String? = extractUsername(jwtToken)
        return (username == userDetails.username) && !isTokenExpired(jwtToken)
    }

    private fun isTokenExpired(jwtToken: String): Boolean {
        return extractExpiration(jwtToken).before(Date())
    }

    private fun extractExpiration(jwtToken: String): Date {
        return extractClaim(jwtToken) { obj: Claims -> obj.expiration }
    }

    private fun extractAllClaims(jwtToken: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun getSignInKey(): Key {
        // stores the decoded the secret key
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)
        // Returns the created new SecretKey instance for use with HMAC-SHA algorithms based on the specified key byte array
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
