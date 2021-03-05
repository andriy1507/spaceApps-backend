package com.spaceapps.backend.services

import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.AuthRequestDto.*
import com.spaceapps.backend.model.dto.AuthTokenDto
import com.spaceapps.backend.model.exceptions.UsernameExistsException
import org.springframework.security.core.userdetails.UsernameNotFoundException

interface AuthorizationService {

    @Throws(UsernameNotFoundException::class)
    fun signInUserNamePassword(userName: String, password: String, device: UserDeviceDto): AuthTokenDto

    @Throws(UsernameExistsException::class)
    fun signUpUserNamePassword(userName: String, password: String, device: UserDeviceDto): AuthTokenDto

    fun refreshToken(token: String): AuthTokenDto

    fun addDeviceToken(device: UserDeviceDto, user: ApplicationUser)

}