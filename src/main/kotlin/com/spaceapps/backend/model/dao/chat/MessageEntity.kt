package com.spaceapps.backend.model.dao.chat

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class MessageEntity(
    @Id
    var id: String = "",
    var text: String = "",
    var dateTime: LocalDateTime = LocalDateTime.now(),
    var conversationId: String = ""
)