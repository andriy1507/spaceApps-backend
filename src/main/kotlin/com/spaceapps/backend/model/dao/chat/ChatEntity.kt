package com.spaceapps.backend.model.dao.chat

import javax.persistence.*

@Entity
@Table(name = "chats")
data class ChatEntity(
    @Id
    @Column(name = "id")
    var id: String = "",
    @Column(name = "name")
    var name: String = ""
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    val messages: MutableList<MessageEntity> = mutableListOf()
}