package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.auth.ResetTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ResetTokenRepository : CrudRepository<ResetTokenEntity, Int> {
    fun getByTokenAndEmail(token: String, email: String): ResetTokenEntity?
}