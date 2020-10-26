package com.spaceapps.backend.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val Any.LOGGER: Logger
    get() = LoggerFactory.getLogger(this::class.java)
