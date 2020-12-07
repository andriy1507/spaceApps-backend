package com.spaceapps.backend.services

interface PushNotificationService {

    fun sendSimpleNotification(
            title: String? = null,
            text: String,
            imageUrl: String? = null,
            token: String
    )

}