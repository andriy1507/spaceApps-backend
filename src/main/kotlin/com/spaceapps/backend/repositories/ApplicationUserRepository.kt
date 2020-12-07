package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.ApplicationUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationUserRepository : CrudRepository<ApplicationUser, Long> {

    fun findAll(pageable: Pageable): Page<ApplicationUser>

    fun getByUserName(name: String): ApplicationUser?

    fun findAllBySubscribersContains(subscriber: ApplicationUser)
}