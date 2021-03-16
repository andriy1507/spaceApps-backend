package com.spaceapps.backend.service

import com.spaceapps.backend.INVALID_RESET_CODE
import com.spaceapps.backend.model.dao.auth.ResetTokenEntity
import com.spaceapps.backend.repository.ResetTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ResetPasswordService @Autowired private constructor(
    private val resetTokenRepository: ResetTokenRepository,
    private val userService: UserService,
    private val mailSenderService: MailSenderService,
    private val taskScheduler: TaskScheduler
) {

    private val generatedToken: String
        get() {
            val numbers = 0..9
            return List(6) { numbers.random() }.joinToString(separator = "")
        }

    fun changePassword(token: String, email: String, newPassword: String): ResponseEntity<*> {
        return if (verifyResetToken(token, email)) {
            userService.changePassword(email, newPassword)
            ResponseEntity.ok().body(null)
        } else {
            ResponseEntity.badRequest().body(INVALID_RESET_CODE)
        }
    }

    fun verifyResetToken(token: String, email: String): Boolean {
        val resetToken = resetTokenRepository.getByTokenAndEmail(token, email)
        return resetToken?.expires?.isAfter(LocalDateTime.now()) ?: false
    }

    fun sendResetToken(email: String) {
        val expires = LocalDateTime.now().plusMinutes(10)
        val entity = resetTokenRepository.save(
            ResetTokenEntity(
                email = email,
                expires = expires,
                token = generatedToken
            )
        )
        mailSenderService.sendResetTokenMail(entity.token, entity.email)
    }

}