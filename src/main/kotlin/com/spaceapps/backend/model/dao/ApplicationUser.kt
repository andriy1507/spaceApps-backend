package com.spaceapps.backend.model.dao

import com.google.gson.annotations.SerializedName
import org.springframework.security.core.userdetails.User
import javax.persistence.*

@Entity
@Table
data class ApplicationUser(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,
        @Column(unique = true)
        @SerializedName("userName")
        val userName: String = "root",
        @Column
        @SerializedName("password")
        val pass: String = "root"
): User(userName, pass, emptyList())