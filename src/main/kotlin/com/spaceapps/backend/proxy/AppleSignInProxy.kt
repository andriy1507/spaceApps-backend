package com.spaceapps.backend.proxy

import com.spaceapps.backend.config.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "Apple", url = "https://appleid.apple.com", configuration = [FeignConfig::class])
interface AppleSignInProxy {

    @PostMapping("/auth/token")
    fun getAppleUserDetails(
        @RequestParam("code") accessToken: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("grant_type") grantType: String = "authorization_code"
    ): ResponseEntity<*>

}