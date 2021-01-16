package com.spaceapps.backend.model.dao

import com.spaceapps.backend.model.dto.ApplicationUserDto
import org.springframework.security.core.userdetails.User
import javax.persistence.*

@Entity
@Table
data class ApplicationUser(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column
        var id: Long = 0,
        @Column(unique = true)
        val userName: String = "root",
        @Column
        val pass: String = "root"
) : User(userName, pass, emptyList()) {
    fun toDto() = ApplicationUserDto(id, userName, pass)

    @OneToMany
    val subscriptions = mutableListOf<SubscriptionDao>()

    @OneToMany
    val devices = mutableListOf<UserDeviceDao>()

}