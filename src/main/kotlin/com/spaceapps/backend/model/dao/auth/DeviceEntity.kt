package com.spaceapps.backend.model.dao.auth

import javax.persistence.*

@Entity
@Table(name = "devices")
data class DeviceEntity(
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "device_token")
    var token: String = "",
    @Column(name = "device_platform")
    var platform:String = ""
)