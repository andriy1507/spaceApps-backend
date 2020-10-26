package com.spaceapps.backend.proxy

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "host", url = "https://develop-space-apps.herokuapp.com/")
interface SelfWakeProxy {

    @GetMapping("/")
    fun getRoot(): String
}