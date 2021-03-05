package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName

data class AuthRequestDto(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("device")
        val device: UserDeviceDto
) {
    data class UserDeviceDto(
            @SerializedName("token")
            val token: String,
            @SerializedName("platform")
            val platform: Platform
    ) {
        enum class Platform(val value:String) {
            @SerializedName("android")
            Android("android"),
            @SerializedName("ios")
            Ios("ios")
        }
    }
}