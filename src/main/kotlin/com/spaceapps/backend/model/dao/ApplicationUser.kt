package com.spaceapps.backend.model.dao

import com.spaceapps.backend.model.dto.ApplicationUserDto
import org.springframework.security.core.userdetails.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class ApplicationUser(
        @Id
        @Column
        var id: Long = 0,
        @Column(unique = true)
        val userName: String = "root",
        @Column
        val pass: String = "root"
) : User(userName, pass, emptyList()) {
    fun toDto() = ApplicationUserDto(id, userName, pass)
}