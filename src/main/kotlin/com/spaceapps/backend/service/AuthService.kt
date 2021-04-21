package com.spaceapps.backend.service

import com.spaceapps.backend.USER_ALREADY_EXISTS
import com.spaceapps.backend.USER_DOES_NOT_EXISTS
import com.spaceapps.backend.WRONG_PASSWORD
import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.model.dto.auth.AuthorizationRequest
import com.spaceapps.backend.model.dto.auth.AuthorizationTokenResponse
import com.spaceapps.backend.model.dto.auth.DeviceRequest
import com.spaceapps.backend.repository.UserRepository
import com.spaceapps.backend.utils.ApplicationUserDetails
import com.spaceapps.backend.utils.JsonWebTokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthService @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val devicesService: DevicesService
) {

    fun signIn(request: AuthorizationRequest): AuthorizationTokenResponse {
        val user = userRepository.getByEmail(request.email)
        user ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, USER_DOES_NOT_EXISTS)
        if (!passwordEncoder.matches(
                request.password,
                user.password
            )
        ) throw ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_PASSWORD)
        devicesService.saveDeviceForUser(user, request.device)
        return generateAuthResponse(user, request.device)
    }

    fun signUp(request: AuthorizationRequest): AuthorizationTokenResponse {
        if (userRepository.existsByEmail(request.email)) throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            USER_ALREADY_EXISTS
        )
        val user = createUserByEmailAndPassword(request.email, request.password)
        devicesService.saveDeviceForUser(user, request.device)
        return JsonWebTokenUtils.generateAuthTokenResponse(user, request.device)
    }

    fun logOut(auth: Authentication, token: String) {
        val user = userRepository.findById((auth.principal as ApplicationUserDetails).userId).get()
        devicesService.deleteDeviceForUser(user, token)
    }

    private fun generateAuthResponse(user: UserEntity, device: DeviceRequest): AuthorizationTokenResponse {
        return JsonWebTokenUtils.generateAuthTokenResponse(user, device)
    }

    private fun createUserByEmailAndPassword(email: String, rawPassword: String): UserEntity {
        userRepository
        return userRepository.save(
            UserEntity(
                email = email,
                password = passwordEncoder.encode(rawPassword)
            )
        )
    }

    fun addDevice(auth: Authentication, request: DeviceRequest) {
        val user = userRepository.findById((auth.principal as ApplicationUserDetails).userId)
        if (!user.isPresent) throw ResponseStatusException(HttpStatus.BAD_REQUEST, USER_DOES_NOT_EXISTS)
        devicesService.saveDeviceForUser(user.get(), request)
    }
}
