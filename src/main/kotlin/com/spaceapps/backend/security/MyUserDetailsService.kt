package com.spaceapps.backend.security

import com.spaceapps.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService @Autowired constructor(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepository.getByEmail(username)
        user ?: throw UsernameNotFoundException("")
        return User(user.email, user.password, emptyList())
    }
}