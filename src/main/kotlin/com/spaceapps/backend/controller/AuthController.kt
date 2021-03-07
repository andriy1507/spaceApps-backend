package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.auth.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
    @ApiOperation("Generates access token and refresh token")
    fun signIn(@RequestBody request: AuthorizationRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
        )
    }

    @PostMapping("/sign-up")
    @ApiOperation("Creates new user. Generates access token and refresh token")
    fun signUp(@RequestBody request: AuthorizationRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
        )
    }

    @PostMapping("/refresh-token")
    @ApiOperation("Refreshes access token and refresh token")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
            "authorization token",
            LocalDateTime.now(),
            "refresh token",
            LocalDateTime.now()
        )
    }

    @PostMapping("/google-sign-in")
    @ApiOperation("Generates access token and refresh token by google access token")
    fun googleSignIn(@RequestBody request: SocialSignInRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
            "authorization token",
            LocalDateTime.now(),
            "refresh token",
            LocalDateTime.now()
        )
    }

    @PostMapping("/facebook-sign-in")
    @ApiOperation("Generates access token and refresh token by facebook access token")
    fun facebookSignIn(@RequestBody request: SocialSignInRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
            "authorization token",
            LocalDateTime.now(),
            "refresh token",
            LocalDateTime.now()
        )
    }

    @PostMapping("/apple-sign-in")
    @ApiOperation("Generates access token and refresh token by apple access token")
    fun appleSignIn(@RequestBody request: SocialSignInRequest): AuthorizationTokenResponse {
        return AuthorizationTokenResponse(
            "authorization token",
            LocalDateTime.now(),
            "refresh token",
            LocalDateTime.now()
        )
    }

    @PostMapping("/send-reset-token")
    @ApiOperation("Sends reset token to email")
    fun sendResetToken(@RequestBody request: SendResetTokenRequest) {

    }

    @PostMapping("/verify-reset-token")
    @ApiOperation("Verifies reset token")
    fun verifyResetToken(@RequestBody request: VerifyResetTokenRequest) {

    }

    @PostMapping("/reset-password")
    @ApiOperation("Sets new password by received code")
    fun resetPassword(@RequestBody request: ResetPasswordRequest) {

    }

    @PostMapping("/add-device")
    @ApiOperation("Adds new user device")
    fun addDevice(@RequestBody request: DeviceRequest) {

    }

    @PostMapping("/log-out")
    @ApiOperation("Removes user device")
    fun logOut(@RequestBody request: DeviceRequest) {

    }
}