package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.dao.UserTaskDao
import com.spaceapps.backend.model.dto.UserTaskDto
import com.spaceapps.backend.model.exceptions.NotFoundException
import com.spaceapps.backend.services.UserTasksService
import com.spaceapps.backend.utils.LOGGER
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tasks")
@Api(value = "User Tasks Controller", tags = ["User Tasks"], description = "User Tasks management")
class TasksController constructor(
        private val tasksService: UserTasksService
) {

    @GetMapping("/")
    fun getAllUserTasks() {

    }

    @PostMapping("/")
    fun addTask(@RequestBody task: UserTaskDto): ResponseEntity<*> {
        LOGGER.info(task.toString())
        return try {
            ResponseEntity.ok(tasksService.saveTask(task))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().body(e.localizedMessage)
        }
    }

    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(tasksService.deleteTaskById(taskId))
        } catch (e: AccessDeniedException) {
            ResponseEntity(e.localizedMessage, HttpStatus.FORBIDDEN)
        } catch (e: NotFoundException) {
            ResponseEntity(e.localizedMessage, HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/")
    fun putTask(task: UserTaskDao): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(tasksService.updateTask(task))
        } catch (e: AccessDeniedException) {
            ResponseEntity(e.localizedMessage, HttpStatus.FORBIDDEN)
        }
    }

}