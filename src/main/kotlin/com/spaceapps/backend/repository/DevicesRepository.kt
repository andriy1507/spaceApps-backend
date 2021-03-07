package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.UserDevice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DevicesRepository : CrudRepository<UserDevice, Long> {

    fun deleteByToken(token: String)

}