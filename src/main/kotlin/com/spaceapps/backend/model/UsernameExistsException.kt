package com.spaceapps.backend.model

import org.springframework.security.core.AuthenticationException

class UsernameExistsException(userName: String) : AuthenticationException("Username '$userName' already exists")