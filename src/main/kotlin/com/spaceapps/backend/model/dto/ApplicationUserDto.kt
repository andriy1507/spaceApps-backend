package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName

class ApplicationUserDto(
        @SerializedName("id")
        var id: Long = 0,
        @SerializedName("userName")
        val userName: String = "root",
        @SerializedName("password")
        val pass: String = "root"
)