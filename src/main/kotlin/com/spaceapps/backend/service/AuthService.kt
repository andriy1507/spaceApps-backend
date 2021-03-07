package com.spaceapps.backend.service

import com.spaceapps.backend.model.dto.auth.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService {

    fun signIn(request: AuthorizationRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun signUp(request: AuthorizationRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun refreshToken(request: RefreshTokenRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun googleSignIn(request: SocialSignInRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun facebookSignIn(request: SocialSignInRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun appleSignIn(request: SocialSignInRequest): ResponseEntity<*> {
        return ResponseEntity.ok(
            AuthorizationTokenResponse(
                "authorization token",
                LocalDateTime.now(),
                "refresh token",
                LocalDateTime.now()
            )
        )
    }

    fun addDevice(request: DeviceRequest): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

    fun logOut(request: DeviceRequest): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

    fun sendResetToken(request: SendResetTokenRequest): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

    fun verifyResetToken(request: VerifyResetTokenRequest): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

    fun resetPassword(request: ResetPasswordRequest): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

}