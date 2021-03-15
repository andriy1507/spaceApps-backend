package com.spaceapps.backend.service

import com.spaceapps.backend.config.properties.MailServiceProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class MailSenderService @Autowired constructor(
    private val properties: MailServiceProperties,
    private val templateEngine: SpringTemplateEngine
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

    fun sendResetTokenMail(resetToken: String, email: String) {
        val mailMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(mailMessage, true, Charsets.UTF_8.name())
        val context = Context()
        context.setVariable("resetToken", resetToken)
        val text = templateEngine.process("reset-token-email", context)
        helper.setTo(email)
        helper.setSubject("Password reset")
        helper.setText(text, true)
        mailSender.send(mailMessage)
    }
}