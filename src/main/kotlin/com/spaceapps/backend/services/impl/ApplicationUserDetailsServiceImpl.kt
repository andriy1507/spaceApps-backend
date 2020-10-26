package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.ApplicationUser
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.services.ApplicationUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ApplicationUserDetailsServiceImpl(
        private val userRepository: ApplicationUserRepository,
        private val passwordEncoder: PasswordEncoder
) : ApplicationUserDetailsService {

    override fun saveUser(user: ApplicationUser) {
        userRepository.saveUser(user.copy(password = passwordEncoder.encode(user.password)))
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.getByUserName(username.orEmpty())?.let { user ->
            User(user.userName, user.password, mutableListOf())
        } ?: throw UsernameNotFoundException("User with username $username not found")
    }
}