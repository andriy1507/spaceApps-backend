package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.UserDeviceDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDevicesRepository : CrudRepository<UserDeviceDao, Long> {

}