package com.spaceapps.backend.services

interface PushNotificationService {

    fun sendSimpleNotification(
            title: String? = null,
            text: String,
            imageUrl: String? = null,
            token: String
    )

    fun sendToUser(title: String?, text: String, imageUrl: String?, userId: Long)
}