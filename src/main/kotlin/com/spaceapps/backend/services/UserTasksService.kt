package com.spaceapps.backend.services

import com.spaceapps.backend.model.dao.UserTaskDao
import com.spaceapps.backend.model.dto.UserTaskDto

interface UserTasksService {

    fun saveTask(task: UserTaskDto): UserTaskDto

    fun deleteTaskById(taskId: Int): Int

    fun updateTask(task: UserTaskDao): UserTaskDto
}