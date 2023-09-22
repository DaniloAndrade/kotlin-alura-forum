package d.andrade.forum.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTUtil(
    @Value("\${jwt.secret}") val secret: String
) {

    fun generateToken(username: String) : String {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(
                Date(
                    System.currentTimeMillis() +
                            Duration.ofHours(1).toMillis()
                )
            ).signWith(secretKey())
            .compact()
    }

    private fun secretKey(): SecretKey? = Keys.hmacShaKeyFor(secret.toByteArray())

    fun extractUsername(token: String): String? {
        return extractClaim(token) {claims ->  claims.subject }
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        val username = extractUsername(token!!)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(secretKey())
            .build()
            .parseClaimsJws(token)
            .body
    }
}