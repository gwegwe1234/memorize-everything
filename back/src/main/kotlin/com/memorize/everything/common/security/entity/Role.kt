package com.memorize.everything.common.security.entity

import javax.persistence.*

@Entity
@Table(name="role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    @ManyToMany(mappedBy = "roles")
    var users: Set<User> = emptySet()
) {
    override fun toString(): String {
        return "Role(id=$id, name=$name)"
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
