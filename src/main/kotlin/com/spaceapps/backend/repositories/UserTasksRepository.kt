package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.UserTaskDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTasksRepository : CrudRepository<UserTaskDao, Int> {

    fun findUserTaskDaoById(id: Int): UserTaskDao?

    fun findUserTaskDaosByUserId(userId: Int):List<UserTaskDao>

}