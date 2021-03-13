package com.spaceapps.backend.proxy

import com.spaceapps.backend.config.FeignConfig
import com.spaceapps.backend.model.dto.auth.social.FacebookSignInResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "Facebook", url = "https://graph.facebook.com", configuration = [FeignConfig::class])
interface FacebookSignInProxy {

    @GetMapping("/me")
    fun getFacebookUserDetails(
        @RequestParam("access_token") accessToken: String,
        @RequestParam("fields") fields: String = "email,name"
    ): FacebookSignInResponse

}