package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.ApplicationUser

interface ApplicationUserRepository {

    fun saveUser(user: ApplicationUser)

    fun getByUserName(name: String): ApplicationUser?
}