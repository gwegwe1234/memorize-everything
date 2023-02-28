package com.memorize.everything.common.security.entity

import javax.persistence.*

@Entity
@Table(name="user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    var password: String,
    @ManyToMany(cascade = [CascadeType.ALL])
    var roles: Set<Role> = emptySet()
) {
    override fun toString(): String {
        return "User(id=$id, username=$username, password=$password)"
    }
}