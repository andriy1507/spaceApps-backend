package com.spaceapps.backend.controllers

import com.spaceapps.backend.config.security.token.AuthTokenProvider
import com.spaceapps.backend.model.ApplicationUser
import com.spaceapps.backend.services.ApplicationUserDetailsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@Api(value = "Root Controller", tags = ["Root"], description = "Root endpoints")
class RootController @Autowired constructor(
        private val service: ApplicationUserDetailsService,
        private val authTokenProvider: AuthTokenProvider
) {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns \"Hello world\".")
    fun getRoot(): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
    }

    @GetMapping("/token")
    fun getUserToken(userName: String, password: String): String {
        val user = ApplicationUser(1, userName, password)
        service.saveUser(user)
        return authTokenProvider.getAuthToken(user)
    }

    @GetMapping("/date-time-now")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns current date-time.")
    fun getDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    @GetMapping("/date-now")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns current date.")
    fun getDate(): LocalDate {
        return LocalDate.now()
    }
}