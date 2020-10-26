package com.spaceapps.backend.model

import com.google.gson.annotations.SerializedName

data class ApplicationUser(
        @SerializedName("userName")
        val userName: String = "root",
        @SerializedName("password")
        val password: String = "root"
)