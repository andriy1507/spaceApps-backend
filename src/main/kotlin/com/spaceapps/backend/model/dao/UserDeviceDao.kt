package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table
data class UserDeviceDao(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Column(name = "fcmToken")
        var fcmToken: String = ""
)