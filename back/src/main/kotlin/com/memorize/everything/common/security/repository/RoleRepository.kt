package com.memorize.everything.common.security.repository

import com.memorize.everything.common.security.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}