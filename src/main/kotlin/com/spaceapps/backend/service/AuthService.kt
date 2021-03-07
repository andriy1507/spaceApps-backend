package com.spaceapps.backend.service

import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.auth.*
import com.spaceapps.backend.repository.DevicesRepository
import com.spaceapps.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService @Autowired constructor(
    private val userRepository: UserRepository,
    private val deviceRepository: DevicesRepository
) {

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

    fun addDevice(request: DeviceRequest, user: ApplicationUser): ResponseEntity<*> {
        return ResponseEntity.ok(Unit)
    }

    fun logOut(request: DeviceRequest, user: ApplicationUser): ResponseEntity<*> {
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