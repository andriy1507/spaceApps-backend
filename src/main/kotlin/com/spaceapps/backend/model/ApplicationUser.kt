package com.spaceapps.backend.model

import com.google.gson.annotations.SerializedName
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
        val password: String = "root"
)