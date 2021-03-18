package com.spaceapps.backend.model.dao.chat

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class MessageEntity(
    @Id
    @Column(name = "id")
    var id: String = "",
    @Column(name = "text")
    var text: String = "",
    @Column(name = "date_time")
    var dateTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "chat_id")
    var conversationId: String = ""
)