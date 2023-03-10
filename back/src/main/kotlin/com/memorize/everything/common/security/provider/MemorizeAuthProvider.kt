package com.memorize.everything.common.security.provider

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.Base64

@Component
class MemorizeAuthProvider(
    private val userDetailsService: UserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val username = decodeBase64(authentication.name)
        val password = decodeBase64(authentication.credentials.toString())
        val userDetails = userDetailsService.loadUserByUsername(username)

        if (password != userDetails.password) {
            throw BadCredentialsException("Invalid username or password")
        }

        val authorities = userDetails.authorities

        return UsernamePasswordAuthenticationToken(username, null, authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

    private fun decodeBase64(encodedString: String) : String{
        return String(Base64.getDecoder().decode(encodedString))
    }
}