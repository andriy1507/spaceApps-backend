package com.spaceapps.backend.service

import com.spaceapps.backend.*
import com.spaceapps.backend.model.dao.auth.DeviceEntity
import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.model.dto.auth.UserType
import com.spaceapps.backend.model.dto.auth.*
import com.spaceapps.backend.proxy.AppleSignInProxy
import com.spaceapps.backend.proxy.FacebookSignInProxy
import com.spaceapps.backend.proxy.GoogleSignInProxy
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
import java.util.*

@Service
class AuthService @Autowired constructor(
    private val userRepository: UserRepository,
    private val deviceRepository: DevicesRepository,
    private val passwordEncoder: PasswordEncoder,
    private val googleSignInProxy: GoogleSignInProxy,
    private val facebookSignInProxy: FacebookSignInProxy,
    private val appleSignInProxy: AppleSignInProxy
) {

    fun signIn(request: AuthorizationRequest): ResponseEntity<*> {
        if (!request.email.isEmail) return ResponseEntity.badRequest().body(EMAIL_NOT_VALID)
        if (!request.password.isPassword) return ResponseEntity.badRequest().body(PASSWORD_NOT_VALID)
        val user = userRepository.getByEmail(request.email)
        user ?: return ResponseEntity.badRequest().body(USER_DOES_NOT_EXISTS)
        if (!passwordEncoder.matches(request.password, user.password)) return ResponseEntity.badRequest()
            .body(WRONG_PASSWORD)
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(user, request.device))
    }

    fun signUp(request: AuthorizationRequest): ResponseEntity<*> {
        if (!request.email.isEmail) return ResponseEntity.badRequest().body(EMAIL_NOT_VALID)
        if (!request.password.isPassword) return ResponseEntity.badRequest().body(PASSWORD_NOT_VALID)
        if (userRepository.existsByEmail(request.email)) return ResponseEntity.badRequest().body(USER_ALREADY_EXISTS)
        val user =
            userRepository.save(UserEntity(email = request.email, password = passwordEncoder.encode(request.password)))
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(user, request.device))
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
        val response = try {
            googleSignInProxy.getGoogleUserDetails(accessToken = request.accessToken)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e)
        }
        if (!userRepository.existsByEmail(response.email)) {
            val user =
                UserEntity(email = response.email, password = UUID.randomUUID().toString(), type = UserType.Google)
            user.devices.add(DeviceEntity(token = request.device.token, platform = request.device.platform.name))
            userRepository.save(user)
        }
        val savedUser = userRepository.getByEmail(response.email)!!
        if (savedUser.type != UserType.Google) return ResponseEntity.badRequest().body(USER_ALREADY_EXISTS)
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(savedUser, request.device))
    }

    fun facebookSignIn(request: SocialSignInRequest): ResponseEntity<*> {
        val response = try {
            facebookSignInProxy.getFacebookUserDetails(accessToken = request.accessToken)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e)
        }
        if (!userRepository.existsByEmail(response.email)) {
            val user =
                UserEntity(email = response.email, password = UUID.randomUUID().toString(), type = UserType.Facebook)
            user.devices.add(DeviceEntity(token = request.device.token, platform = request.device.platform.name))
            userRepository.save(user)
        }
        val savedUser = userRepository.getByEmail(response.email)!!
        if (savedUser.type != UserType.Facebook) return ResponseEntity.badRequest().body(USER_ALREADY_EXISTS)
        return ResponseEntity.ok(JsonWebTokenUtils.generateAuthTokenResponse(savedUser, request.device))
    }

    fun appleSignIn(request: SocialSignInRequest): ResponseEntity<*> {
        appleSignInProxy.getAppleUserDetails(accessToken = request.accessToken, clientId = "", clientSecret = "")
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