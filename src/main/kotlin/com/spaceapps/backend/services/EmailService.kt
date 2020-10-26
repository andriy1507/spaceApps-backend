package com.spaceapps.backend.services

interface EmailService {

    fun sendEmail(message: String, to: String)
}