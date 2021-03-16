package com.spaceapps.backend.service

import com.spaceapps.backend.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun changePassword(email: String, newPassword: String) {
        userRepository.getByEmail(email)?.let {
            userRepository.save(it.copy(password = passwordEncoder.encode(newPassword)))
        }
    }
}