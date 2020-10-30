package com.spaceapps.backend.services.impl

import com.spaceapps.backend.proxy.SelfWakeProxy
import com.spaceapps.backend.services.SelfWakeService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SelfWakeServiceImpl @Autowired constructor(
        private val selfWakeProxy: SelfWakeProxy
) :SelfWakeService {

    @Scheduled(cron = "0 */15 * * * *")
    override fun selfWake() {
        LOGGER.info("Calling host to wake: ${selfWakeProxy.getRoot()}")
    }
}