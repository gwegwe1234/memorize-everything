package com.memorize.everything.common.security.filter

import com.memorize.everything.common.util.JwtUtils
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Component
class MemorizeAuthFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header != null && header.startsWith("Bearer ")) {
            val token = header.substring(6)
            if (JwtUtils.validateJwtToken(token)) {
                val username = JwtUtils.getUserNameFromJwtToken(token)
                val authorities = JwtUtils.getAuthoritiesFromJwtToken(token).split(",")
                    .map { SimpleGrantedAuthority(it) }
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(username, null, authorities)
                filterChain.doFilter(request,response)
            } else {
                logger.info("invalid jwt")
                SecurityContextHolder.clearContext()
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                return
            }
        }
    }
}