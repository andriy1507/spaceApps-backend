package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.auth.DeviceEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DevicesRepository : CrudRepository<DeviceEntity, Int> {

    fun findByToken(token: String): DeviceEntity?

    fun existsByToken(token: String): Boolean
}