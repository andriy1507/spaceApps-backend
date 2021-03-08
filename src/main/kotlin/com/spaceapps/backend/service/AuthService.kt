package com.spaceapps.backend.service

import com.spaceapps.backend.*
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dao.UserDevice
import com.spaceapps.backend.model.dto.auth.*
import com.spaceapps.backend.repository.DevicesRepository
import com.spaceapps.backend.repository.UserRepository
import com.spaceapps.backend.utils.JsonWebTokenUtils
import com.spaceapps.backend.utils.isEmail
import com.spaceapps.backend.utils.isPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService @Autowired constructor(
    private val userRepository: UserRepository,
    private val deviceRepository: DevicesRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun signIn(request: AuthorizationRequest): ResponseEntity<*> {
        if (!request.email.isEmail) return ResponseEntity.badRequest().body(EMAIL_NOT_VALID)
        if (!request.password.isPassword) return ResponseEntity.badRequest().body(PASSWORD_NOT_VALID)
        val user = userRepository.getByEmail(request.email)
        user ?: return ResponseEntity.badRequest().body(USER_DOES_NOT_EXISTS)
        if (!passwordEncoder.matches(request.password, user.password)) return ResponseEntity.badRequest().body(WRONG_PASSWORD)
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(user))
    }

    fun signUp(request: AuthorizationRequest): ResponseEntity<*> {
        if (!request.email.isEmail) return ResponseEntity.badRequest().body(EMAIL_NOT_VALID)
        if (!request.password.isPassword) return ResponseEntity.badRequest().body(PASSWORD_NOT_VALID)
        if (userRepository.existsByEmail(request.email)) return ResponseEntity.badRequest().body(USER_ALREADY_EXISTS)
        val user = userRepository.save(ApplicationUser(email = request.email, password = passwordEncoder.encode(request.password)))
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(user))
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