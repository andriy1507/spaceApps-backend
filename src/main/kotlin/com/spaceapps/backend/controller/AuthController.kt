package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.auth.*
import com.spaceapps.backend.service.AuthService
import com.spaceapps.backend.utils.ApplicationUserDetails
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
@Api(tags = ["Authorization"], description = "Authorization endpoints")
@RequestMapping("auth")
class AuthController @Autowired constructor(
    private val authService: AuthService
) {

    @PostMapping("/sign-in")
    @ApiOperation("Generates access token and refresh token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun signIn(@RequestBody request: AuthorizationRequest): ResponseEntity<*> {
        return authService.signIn(request)
    }

    @PostMapping("/sign-up")
    @ApiOperation("Creates new user. Generates access token and refresh token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun signUp(@RequestBody request: AuthorizationRequest): ResponseEntity<*> {
        return authService.signUp(request)
    }

    @PostMapping("/refresh-token")
    @ApiOperation("Refreshes access token and refresh token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<*> {
        return authService.refreshToken(request)
    }

    @PostMapping("/google-sign-in")
    @ApiOperation("Generates access token and refresh token by google access token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun googleSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.googleSignIn(request)
    }

    @PostMapping("/facebook-sign-in")
    @ApiOperation("Generates access token and refresh token by facebook access token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun facebookSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.facebookSignIn(request)
    }

    @PostMapping("/apple-sign-in")
    @ApiOperation("Generates access token and refresh token by apple access token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = AuthorizationTokenResponse::class))
    fun appleSignIn(@RequestBody request: SocialSignInRequest): ResponseEntity<*> {
        return authService.appleSignIn(request)
    }

    @PostMapping("/send-reset-token")
    @ApiOperation("Sends reset token to email")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun sendResetToken(@RequestBody request: SendResetTokenRequest): ResponseEntity<*> {
        return authService.sendResetToken(request)
    }

    @PostMapping("/verify-reset-token")
    @ApiOperation("Verifies reset token")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun verifyResetToken(@RequestBody request: VerifyResetTokenRequest): ResponseEntity<*> {
        return authService.verifyResetToken(request)
    }

    @PutMapping("/reset-password")
    @ApiOperation("Sets new password by received code")
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun resetPassword(@RequestBody request: ResetPasswordRequest): ResponseEntity<*> {
        return authService.resetPassword(request)
    }

    @PostMapping("/add-device")
    @ApiOperation("Adds new user device")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun addDevice(
        @RequestBody request: DeviceRequest,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return authService.addDevice(request, user)
    }

    @DeleteMapping("/log-out/{deviceToken}")
    @ApiOperation("Removes user device")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun logOut(
        @PathVariable("deviceToken") deviceToken: String,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return authService.logOut(deviceToken, user)
    }
}