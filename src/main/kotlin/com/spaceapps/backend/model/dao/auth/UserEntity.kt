package com.spaceapps.backend.model.dao.auth

import com.spaceapps.backend.model.dto.auth.UserType
import java.time.LocalDate
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
    var type: UserType = UserType.SpaceApps,
    @Column(name = "first_name")
    var firstName: String? = null,
    @Column(name = "last_name")
    var lastName: String? = null,
    @Column(name = "image_url")
    var imageUrl: String? = null,
    @Column(name = "birth_date")
    var birthDate: LocalDate? = null,
    @Column(name = "phone")
    var phone: String? = null,
    @Column(name = "address")
    var address: String? = null,
    @Column(name = "city")
    var city: String? = null,
    @Column(name = "region")
    var region: String? = null,
    @Column(name = "country")
    var country: String? = null,
    @Column(name = "zip_code")
    var zipCode: String? = null
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val devices: MutableSet<DeviceEntity> = mutableSetOf()
}