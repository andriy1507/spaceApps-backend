package com.spaceapps.backend

import com.google.common.truth.Truth.assertThat
import com.spaceapps.backend.controller.AuthController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    var authController: AuthController? = null

    @Test
    fun contextLoads() {
        assertThat(authController).isNotNull()
    }
}
