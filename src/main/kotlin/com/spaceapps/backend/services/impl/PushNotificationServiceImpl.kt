package com.spaceapps.backend.services.impl

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.spaceapps.backend.config.FcmProperties
import com.spaceapps.backend.repositories.ApplicationUserRepository
import com.spaceapps.backend.repositories.UserDevicesRepository
import com.spaceapps.backend.services.PushNotificationService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import javax.annotation.PostConstruct

@Service
class PushNotificationServiceImpl @Autowired constructor(
        private val properties: FcmProperties,
        private val userRepository: ApplicationUserRepository,
        private val devicesRepository: UserDevicesRepository
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

    override fun sendToUser(title: String?, text: String, imageUrl: String?, userName: String) {
        userRepository.getByUserName(userName)?.let { user ->
            devicesRepository.getAllByUserId(user.id).let { devices ->
                LOGGER.info("${userName}\'s devices: $devices")
                val message = MulticastMessage.builder()
                        .addAllTokens(devices.map { it.fcmToken })
                        .putAllData(mapOf(
                                "title" to title,
                                "text" to text,
                                "imageUrl" to imageUrl
                        )).build()
                val responses = messaging.sendMulticast(message).responses
                LOGGER.info(responses.map { "Response: ${it.messageId} is successful: ${it.isSuccessful}" }.toString())
            }
        }
    }
}