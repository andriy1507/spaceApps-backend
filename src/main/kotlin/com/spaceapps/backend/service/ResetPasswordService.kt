package com.spaceapps.backend.service

import com.spaceapps.backend.model.dao.auth.ResetTokenEntity
import com.spaceapps.backend.repository.ResetTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class ResetPasswordService @Autowired private constructor(
    private val resetTokenRepository: ResetTokenRepository,
    private val mailSenderService: MailSenderService,
    private val taskScheduler: TaskScheduler
) {

    private val generatedToken: String
        get() {
            val numbers = 0..9
            return List(6) { numbers.random() }.joinToString(separator = "")
        }

    fun sendResetToken(email: String) {
        val entity = resetTokenRepository.save(
            ResetTokenEntity(
                email = email,
                expires = LocalDateTime.now().plusMinutes(3L),
                token = generatedToken
            )
        )
        mailSenderService.sendResetTokenMail(entity.token, entity.email)
        taskScheduler.scheduleWithFixedDelay(
            { resetTokenRepository.delete(entity) },
            Duration.ofSeconds(30)
        )
    }

}