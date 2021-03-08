package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.ApplicationUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<ApplicationUser, Int> {

    fun existsByEmail(email: String): Boolean

    fun getByEmail(email: String): ApplicationUser?

}