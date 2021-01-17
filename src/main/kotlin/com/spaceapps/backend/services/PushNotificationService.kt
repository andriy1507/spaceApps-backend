package com.spaceapps.backend.services

import com.spaceapps.backend.model.dto.PushNotificationRequest

interface PushNotificationService {

    fun sendSimpleNotification(
            title: String? = null,
            text: String,
            imageUrl: String? = null,
            token: String
    )

    fun sendToUser(notification: PushNotificationRequest, userName: String)
}