package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dao.UserTaskDao
import com.spaceapps.backend.model.dto.UserTaskDto
import com.spaceapps.backend.model.exceptions.NotFoundException
import com.spaceapps.backend.repositories.UserTasksRepository
import com.spaceapps.backend.services.UserTasksService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserTasksServiceImpl constructor(
        private val tasksRepository: UserTasksRepository
) : UserTasksService {

    override fun saveTask(task: UserTaskDto): UserTaskDto {
        LOGGER.info(task.toString())
        return (SecurityContextHolder.getContext().authentication?.principal as? ApplicationUser)?.id?.let { userId ->
            val taskDao = UserTaskDao(
                    title = task.title,
                    text = task.text,
                    userId = userId.toInt()
            )
            val dao = tasksRepository.save(taskDao)
            return UserTaskDto(
                    dao.userId,
                    dao.id,
                    dao.text.orEmpty(),
                    dao.title.orEmpty()
            )
        } ?: throw object : AuthenticationException("Unauthorized") {}
    }

    override fun deleteTaskById(taskId: Int): Int {
        return (SecurityContextHolder.getContext().authentication?.principal as? ApplicationUser)?.let { user ->
            tasksRepository.findUserTaskDaoById(taskId)?.let { taskDao ->
                if (taskDao.userId == user.id.toInt()) {
                    tasksRepository.deleteById(taskId)
                    return taskId
                } else {
                    throw AccessDeniedException("Access denied")
                }
            } ?: throw NotFoundException("Task with id $taskId not found")
        } ?: throw AccessDeniedException("Unauthorized request")
    }

    override fun updateTask(task: UserTaskDao): UserTaskDto {
        return (SecurityContextHolder.getContext().authentication?.principal as? ApplicationUser)?.let { user ->
            if (tasksRepository.existsById(task.id)) {
                tasksRepository.findUserTaskDaoById(task.id)?.let { taskDao ->
                    if (taskDao.userId == user.id.toInt()) {
                        val dao = tasksRepository.save(UserTaskDao(title = task.title, text = task.text))
                        UserTaskDto(dao.userId, dao.id, dao.text.orEmpty(), dao.title.orEmpty())
                    } else {
                        throw AccessDeniedException("Access denied")
                    }
                }
            } else {
                val dao = tasksRepository.save(UserTaskDao(
                        title = task.title,
                        text = task.text
                ))
                return UserTaskDto(dao.userId, dao.id, dao.text.orEmpty(), dao.title.orEmpty())
            }
        } ?: throw AccessDeniedException("Unauthorized request")
    }
}
