package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.ApplicationUser
import org.springframework.stereotype.Repository

@Repository
class ApplicationUserRepositoryImpl : ApplicationUserRepository {

    private val userList = mutableListOf<ApplicationUser>()

    override fun saveUser(user: ApplicationUser) {
        userList.add(user)
    }

    override fun getByUserName(name: String): ApplicationUser? {
        return userList.firstOrNull { it.userName == name }
    }
}