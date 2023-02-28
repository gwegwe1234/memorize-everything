package com.memorize.everything.common.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.memorize.everything.common.support.ApiResponse
import com.memorize.everything.common.util.JwtUtils
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthSuccessHandler(
    private val objectMapper: ObjectMapper
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val token = JwtUtils.generateJwtToken(authentication)
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ApiResponse.ok(token)))
    }
}