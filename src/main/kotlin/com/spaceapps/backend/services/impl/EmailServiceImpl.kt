package com.spaceapps.backend.services.impl

import com.spaceapps.backend.services.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl @Autowired constructor(
        private val mailSender: MailSender
) : EmailService {

    override fun sendEmail(message: String, to: String) {
        val message = SimpleMailMessage().apply {
            setTo(to)
            setText(message)
        }
        mailSender.send(message)
    }
}