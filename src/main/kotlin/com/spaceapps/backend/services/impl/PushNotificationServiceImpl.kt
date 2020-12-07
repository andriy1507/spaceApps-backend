package com.spaceapps.backend.services.impl

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.spaceapps.backend.config.FcmProperties
import com.spaceapps.backend.services.PushNotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import javax.annotation.PostConstruct

@Service
class PushNotificationServiceImpl @Autowired constructor(
        private val properties: FcmProperties
) : PushNotificationService {

    private lateinit var messaging: FirebaseMessaging

    @PostConstruct
    private fun postConstruct() {
        val serviceAccount = FileInputStream(properties.serviceAccountFile)
        val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(properties.databaseUrl)
                .build()
        val app = FirebaseApp.initializeApp(options)
        messaging = FirebaseMessaging.getInstance(app)
    }

    override fun sendSimpleNotification(title: String?, text: String, imageUrl: String?, token: String) {
        val message = Message.builder()
                .setToken(token)
                .putAllData(mapOf(
                        "title" to title,
                        "text" to text,
                        "imageUrl" to imageUrl
                ))
                .build()
        messaging.send(message)
    }
}