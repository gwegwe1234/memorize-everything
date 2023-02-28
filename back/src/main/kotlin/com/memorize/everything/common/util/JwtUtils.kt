package com.memorize.everything.common.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import java.util.*
import java.util.stream.Collectors

class JwtUtils {

    companion object {
        private const val jwtSecret = "memorizeEverythingSecretkeyneedtoverylongword"
        private const val jwtExpiration = 864000
        private val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

        fun generateJwtToken(authentication: Authentication): String {
            val username = authentication.principal as String
            val authorities = authentication.authorities
                .stream()
                .map { it.authority }
                .collect(Collectors.joining(","))

            return Jwts.builder()
                .claim("Authorities", authorities)
                .setSubject(username)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpiration * 1000))
                .signWith(key)
                .compact()
        }

        fun validateJwtToken(authToken: String): Boolean {
            try {
                Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken)

                return true
            } catch (e: Exception) {
                println("Invalid JWT token: ${e.message}")
            }

            return false
        }

        fun getUserNameFromJwtToken(token: String): String {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        }

        fun getAuthoritiesFromJwtToken(token: String): String {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .get("Authorities", String::class.java)
        }
    }


}