package com.spaceapps.backend.model.dao.auth

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reset_token")
data class ResetTokenEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "email")
    var email: String = "",
    @Column(name = "expires")
    var expires: LocalDateTime = LocalDateTime.now(),
    @Column(name = "token")
    var token: String = ""
)