package com.memorize.everything.common.security.repository

import com.memorize.everything.common.security.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}