package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table(name = "devices")
data class UserDevice(
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "device_token")
    var token: String = "",
    @Column(name = "device_user_id")
    var userId: Int = 0,
    @Column(name = "device_platform")
    var platform:String = ""
)