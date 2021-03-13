package com.spaceapps.backend.model.dao.chat

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "conversations")
data class ConversationEntity(
    @Id
    var id: String = "",
    var name: String = ""
)