package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table(name = "users")
data class ApplicationUser(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "user_email")
    var email: String = "",
    @Column(name = "user_password")
    var password: String = ""
) {
    @OneToMany(targetEntity = UserDevice::class, fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    val devices: MutableList<UserDevice> = mutableListOf()
}