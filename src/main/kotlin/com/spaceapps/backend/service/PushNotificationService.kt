package com.spaceapps.backend.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.*
import com.spaceapps.backend.config.properties.FirebaseProperties
import com.spaceapps.backend.repository.DevicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import javax.annotation.PostConstruct

@Service
class PushNotificationService @Autowired constructor(
    private val properties: FirebaseProperties,
    private val devicesRepository: DevicesRepository
) {

    private lateinit var messaging: FirebaseMessaging
    private val androidConfig = AndroidConfig.builder().build()
    private val apnsConfig = ApnsConfig.builder().setAps(Aps.builder().build()).build()
    private val webPushConfig = WebpushConfig.builder().build()

    @PostConstruct
    fun initialize() {
        val serviceAccount = FileInputStream(properties.serviceAccountFile)
        val options: FirebaseOptions = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl(properties.databaseUrl)
            .build()
        val app = FirebaseApp.initializeApp(options)
        messaging = FirebaseMessaging.getInstance(app)
    }

    fun sendNewPostNotification() {
        val deviceTokens = devicesRepository.findAll().map { it.token }
        val data = buildMessageData("New post available","Open application to see new post", null)
        val multicastMessage = MulticastMessage.builder()
            .addAllTokens(deviceTokens)
            .setAndroidConfig(androidConfig)
            .setApnsConfig(apnsConfig)
            .setWebpushConfig(webPushConfig)
            .putAllData(data)
            .build()
        messaging.sendMulticastAsync(multicastMessage)
    }

    private fun buildMessageData(
        title:String,
        text:String,
        image:String?
    ): MutableMap<String, String> {
        val data = mutableMapOf(
            TITLE_KEY to title,
            TEXT_KEY to text
        )
        image ?: return data
        data[IMAGE_KEY] = image
        return data
    }

    companion object {
        private const val TITLE_KEY = "title"
        private const val TEXT_KEY = "text"
        private const val IMAGE_KEY = "imageUrl"
    }
}