package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.AuthorizationRequest
import com.spaceapps.backend.model.dto.AuthorizationRequest.Device
import com.spaceapps.backend.model.dto.AuthorizationTokenResponse
import com.spaceapps.backend.model.dto.ForgotPasswordRequest
import com.spaceapps.backend.model.dto.RefreshTokenRequest
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@Api(tags = ["Authorization"], description = "Authorization endpoints")
@RequestMapping("authorization")
class AuthController {

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: AuthorizationRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
        )
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: AuthorizationRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
        )
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
        )
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(@RequestBody request: ForgotPasswordRequest) {

    }

    @PostMapping("/add-device")
    fun addDevice(@RequestBody request: Device) {

    }
}