package com.spaceapps.backend.services

import com.spaceapps.backend.model.dao.ApplicationUser
import org.springframework.security.core.userdetails.UserDetailsService

interface ApplicationUserDetailsService : UserDetailsService {

    fun saveUser(user: ApplicationUser)

}