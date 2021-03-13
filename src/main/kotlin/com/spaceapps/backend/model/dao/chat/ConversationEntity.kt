package com.spaceapps.backend.model.dao.chat

import javax.persistence.*

@Entity
@Table(name = "conversations")
data class ConversationEntity(
    @Id
    @Column(name = "id")
    var id: String = "",
    @Column(name = "name")
    var name: String = ""
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    val messages: MutableList<MessageEntity> = mutableListOf()
}