package com.memorize.everything.common.security.service

import com.memorize.everything.common.security.repository.RoleRepository
import com.memorize.everything.common.security.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserDetailServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username")

        val roles = user.roles.map { roleRepository.findByName(it.name)!! }

        return User(
            user.username,
            user.password,
            roles.map { SimpleGrantedAuthority(it.name) }
        )
    }
}