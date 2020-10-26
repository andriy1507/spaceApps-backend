package com.spaceapps.backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Configuration
@EnableScheduling
class SelfWakeUpSchedule {

    @Scheduled(cron = "* */20 * * * *")
    fun callSelf(){
        ServletUriComponentsBuilder.fromCurrentContextPath()
    }
}