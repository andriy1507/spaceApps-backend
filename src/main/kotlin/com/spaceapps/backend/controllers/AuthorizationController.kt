package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.exceptions.UsernameExistsException
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.services.AuthorizationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("authorization")
class AuthorizationController constructor(
        private val authorizationService: AuthorizationService,
        private val userRepository: ApplicationUserRepository
) {

//    @GetMapping("/users")
//    fun getAllUsers(
//            @PageableDefault(
//                    size = 20,
//                    page = 1
//            ) page: Pageable
//    ): PaginationResponse<ApplicationUserDto> {
//        return userRepository.findAll(page).let {
//            PaginationResponse(it.pageable.pageNumber, it.totalElements.toInt(), it.get().map { it.toDto() }.toList())
//        }
//    }

    @GetMapping("/sign-in")
    fun singInWithUserNameAndPassword(
            @RequestParam("userName") userName: String,
            @RequestParam("password") password: String
    ): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(authorizationService.signInUserNamePassword(userName, password))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e)
        }
    }

    @PostMapping("/sign-up")
    fun signUpWithUserNameAndPassword(
            @RequestParam("userName") userName: String,
            @RequestParam("password") password: String
    ): ResponseEntity<*> {
        return try {
            val token = authorizationService.signUpUserNamePassword(userName, password)
            ResponseEntity.ok(token)
        } catch (e: UsernameExistsException) {
            ResponseEntity.badRequest().body(e.localizedMessage)
        }
    }

    @GetMapping("/refresh-token")
    fun refreshToken(@RequestParam("refresh_token") token: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(authorizationService.refreshToken(token))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e)
        }
    }

//    @PostMapping("/forgot-password")
//    fun forgotPassword() {
//
//    }
}