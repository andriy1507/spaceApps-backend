package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.auth.*
import com.spaceapps.backend.service.AuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(tags = ["Authorization"], description = "Authorization endpoints")
@RequestMapping("auth")
class AuthController @Autowired constructor(
    private val authService: AuthService
) {

    @PostMapping("/sign-in")
    @ApiOperation("Generates access token and refresh token")
    fun signIn(@RequestBody request: AuthorizationRequest): ResponseEntity<*> {
        return authService.signIn(request)
    }

    @PostMapping("/sign-up")
    @ApiOperation("Creates new user. Generates access token and refresh token")
    fun signUp(@RequestBody request: AuthorizationRequest): ResponseEntity<*> {
        return authService.signUp(request)
    }

    @PostMapping("/refresh-token")
    @ApiOperation("Refreshes access token and refresh token")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<*> {
        return authService.refreshToken(request)
    }

    @PostMapping("/google-sign-in")
    @ApiOperation("Generates access token and refresh token by google access token")
    fun googleSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.googleSignIn(request)
    }

    @PostMapping("/facebook-sign-in")
    @ApiOperation("Generates access token and refresh token by facebook access token")
    fun facebookSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.facebookSignIn(request)
    }

    @PostMapping("/apple-sign-in")
    @ApiOperation("Generates access token and refresh token by apple access token")
    fun appleSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.appleSignIn(request)
    }

    @PostMapping("/send-reset-token")
    @ApiOperation("Sends reset token to email")
    fun sendResetToken(@RequestBody request: SendResetTokenRequest): ResponseEntity<*> {
        return authService.sendResetToken(request)
    }

    @PostMapping("/verify-reset-token")
    @ApiOperation("Verifies reset token")
    fun verifyResetToken(@RequestBody request: VerifyResetTokenRequest): ResponseEntity<*> {
        return authService.verifyResetToken(request)
    }

    @PutMapping("/reset-password")
    @ApiOperation("Sets new password by received code")
    fun resetPassword(@RequestBody request: ResetPasswordRequest): ResponseEntity<*> {
        return authService.resetPassword(request)
    }

    @PostMapping("/add-device")
    @ApiOperation("Adds new user device")
    fun addDevice(@RequestBody request: DeviceRequest): ResponseEntity<*> {
        return authService.addDevice(request)
    }

    @PostMapping("/log-out")
    @ApiOperation("Removes user device")
    fun logOut(@RequestBody request: DeviceRequest): ResponseEntity<*> {
        return authService.logOut(request)
    }
}