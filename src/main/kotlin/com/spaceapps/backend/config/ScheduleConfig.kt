package com.spaceapps.backend.config

import com.spaceapps.backend.proxy.SelfWakeProxy
import com.spaceapps.backend.utils.LOGGER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar

@Configuration
@EnableScheduling
class ScheduleConfig(
        private val selfWakeClient: SelfWakeProxy
) : SchedulingConfigurer {

    @Scheduled(cron = "* */20 * * * *")
    fun selfWake() {
        LOGGER.info("Calling host to wake: ${selfWakeClient.getRoot()}")
    }

    @Bean
    fun taskScheduler(): ThreadPoolTaskScheduler {
        return ThreadPoolTaskScheduler().apply {
            initialize()
        }
    }

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler())
    }
}