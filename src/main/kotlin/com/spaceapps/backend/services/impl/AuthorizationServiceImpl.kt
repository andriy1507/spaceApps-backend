package com.spaceapps.backend.services.impl

import com.spaceapps.backend.config.security.token.AuthTokenProvider
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dao.UserDeviceDao
import com.spaceapps.backend.model.dto.AuthRequestDto
import com.spaceapps.backend.model.dto.AuthRequestDto.*
import com.spaceapps.backend.model.dto.AuthTokenDto
import com.spaceapps.backend.model.exceptions.UsernameExistsException
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.repositories.UserDevicesRepository
import com.spaceapps.backend.services.AuthorizationService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.security.auth.login.CredentialException

@Service
class AuthorizationServiceImpl constructor(
        private val userRepository: ApplicationUserRepository,
        private val devicesRepository: UserDevicesRepository,
        private val passwordEncoder: PasswordEncoder,
        private val tokenProvider: AuthTokenProvider
) : AuthorizationService {

    override fun signInUserNamePassword(userName: String, password: String, device: UserDeviceDto): AuthTokenDto {
        return userRepository.getByUserName(userName)?.let { user ->
            if (passwordEncoder.matches(password, user.pass)) {
                addDeviceToken(device, user)
                tokenProvider.getAuthTokenDto(user)
            } else {
                throw CredentialException("Wrong password for user $userName")
            }
        } ?: throw UsernameNotFoundException("User with name $userName does not exists")
    }

    override fun signUpUserNamePassword(userName: String, password: String, device: UserDeviceDto): AuthTokenDto {
        return try {
            val user = userRepository.save(ApplicationUser(userName = userName, pass = passwordEncoder.encode(password)))
            addDeviceToken(device, user)
            tokenProvider.getAuthTokenDto(user)
        } catch (e: Exception) {
            LOGGER.error(e.localizedMessage)
            e.printStackTrace()
            throw UsernameExistsException(userName)
        }
    }

    override fun refreshToken(token: String): AuthTokenDto {
        return tokenProvider.isRefreshTokenValid(token)?.let { userName ->
            userRepository.getByUserName(userName)?.let {
                tokenProvider.getAuthTokenDto(it)
            } ?: throw UsernameNotFoundException("User with name $userName not found")
        } ?: throw Exception("Refresh token is not valid")
    }

    override fun addDeviceToken(device: UserDeviceDto, user: ApplicationUser) {
        userRepository.getByUserName(user.userName)?.let {
            val deviceDao = devicesRepository.save(UserDeviceDao(fcmToken = device.token, userId = user.id, platform = device.platform.value))
            it.devices.add(deviceDao)
            userRepository.save(it)
        }
    }
}