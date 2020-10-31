package com.spaceapps.backend.services

import com.spaceapps.backend.model.exceptions.UsernameExistsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import kotlin.jvm.Throws

interface AuthorizationService {

    @Throws(UsernameNotFoundException::class)
    fun signInUserNamePassword(userName: String, password: String): String

    @Throws(UsernameExistsException::class)
    fun signUpUserNamePassword(userName: String, password: String): String

}