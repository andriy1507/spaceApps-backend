package com.spaceapps.backend.proxy

import com.spaceapps.backend.config.FeignConfig
import com.spaceapps.backend.model.dto.auth.social.GoogleSignInResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "Google", url = "https://oauth2.googleapis.com", configuration = [FeignConfig::class])
interface GoogleSignInProxy {

    @GetMapping("/tokeninfo")
    fun getGoogleUserDetails(
        @RequestParam("id_token") accessToken: String
    ): GoogleSignInResponse
}