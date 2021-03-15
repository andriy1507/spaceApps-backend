package com.spaceapps.backend.utils

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class ApplicationUserDetails(
    val userId: Int,
    username: String,
    credentials: String,
    authorities: Collection<GrantedAuthority>
) : User(username, credentials, authorities)