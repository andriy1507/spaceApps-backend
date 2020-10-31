package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.ApplicationUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationUserRepository:CrudRepository<ApplicationUser, Int> {

    fun getByUserName(name: String): ApplicationUser?
}