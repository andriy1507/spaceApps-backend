package com.spaceapps.backend.model.dao.auth

import com.spaceapps.backend.model.dto.auth.UserType
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "user_email")
    var email: String = "",
    @Column(name = "user_password")
    var password: String = "",
    @Column(name = "user_type")
    var type: UserType = UserType.SpaceApps
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "device_user_id")
    val devices: MutableList<DeviceEntity> = mutableListOf()
}