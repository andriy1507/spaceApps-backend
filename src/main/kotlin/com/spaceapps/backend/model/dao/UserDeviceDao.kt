package com.spaceapps.backend.model.dao

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class UserDeviceDao(
        @Id
        @Column(name = "fcmToken", unique = true)
        var fcmToken: String = "",
        @Column(name = "applicationUserId")
        var userId: Long = 0,
        @Column(name = "platform")
        var platform: String
)