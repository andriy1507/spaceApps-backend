package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.UsernameExistsException
import com.spaceapps.backend.services.AuthorizationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.SQLException

@RestController
@RequestMapping("authorization")
class AuthorizationController constructor(
        private val authorizationService: AuthorizationService
) {

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

//    @PostMapping("/forgot-password")
//    fun forgotPassword() {
//
//    }
}