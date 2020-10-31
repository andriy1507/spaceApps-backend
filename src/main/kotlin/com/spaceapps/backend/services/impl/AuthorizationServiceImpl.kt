package com.spaceapps.backend.services.impl

import com.spaceapps.backend.config.security.token.AuthTokenProvider
import com.spaceapps.backend.model.ApplicationUser
import com.spaceapps.backend.model.UsernameExistsException
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.services.AuthorizationService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.security.auth.login.CredentialException

@Service
class AuthorizationServiceImpl constructor(
        private val userRepository: ApplicationUserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val tokenProvider: AuthTokenProvider
) : AuthorizationService {

    override fun signInUserNamePassword(userName: String, password: String): String {
        return userRepository.getByUserName(userName)?.let { user ->
            if (passwordEncoder.matches(password, user.password)) {
                tokenProvider.getAuthToken(user)
            } else {
                throw CredentialException("Wrong password for user $userName")
            }
        } ?: throw UsernameNotFoundException("User with name $userName does not exists")
    }

    override fun signUpUserNamePassword(userName: String, password: String): String {
        return try {
            val user = ApplicationUser(userName = userName, password = passwordEncoder.encode(password))
            tokenProvider.getAuthToken(userRepository.save(user))
        } catch (e: Exception) {
            throw UsernameExistsException(userName)
        }
    }
}