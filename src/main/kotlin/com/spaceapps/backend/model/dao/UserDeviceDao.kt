package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table
data class UserDeviceDao(
        @Id
        @Column(name = "fcmToken", unique = true)
        var fcmToken: String = "",
        @Column(name = "applicationUserId")
        var userId: Long = 0
)