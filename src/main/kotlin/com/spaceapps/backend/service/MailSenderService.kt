package com.spaceapps.backend.service

import com.spaceapps.backend.config.properties.MailServiceProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service

@Service
class MailSenderService @Autowired constructor(
    private val properties: MailServiceProperties
) {

    private val mailSender = JavaMailSenderImpl().apply {
        host = properties.host
        port = properties.port
        username = properties.username
        password = properties.password
        protocol = properties.protocol
        javaMailProperties["mail.smtp.auth"] = properties.auth
        javaMailProperties["mail.smtp.starttls.enable"] = properties.starttls
    }


    // TODO: 3/15/2021 Add template
    fun sendResetTokenMail(resetToken: String, email: String) {
        val mailMessage = SimpleMailMessage()
        val text = "Your reset token:\n$resetToken"
        mailMessage.setTo(email)
        mailMessage.setSubject("Password reset")
        mailMessage.setText(text)
        mailSender.send(mailMessage)
    }
}