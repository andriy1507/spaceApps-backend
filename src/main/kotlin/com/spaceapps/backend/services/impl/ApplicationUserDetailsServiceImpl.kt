package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.services.ApplicationUserDetailsService
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
        userRepository.save(user.copy(pass = passwordEncoder.encode(user.pass)))
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.getByUserName(username.orEmpty()) ?: throw UsernameNotFoundException("User with username $username not found")
    }
}