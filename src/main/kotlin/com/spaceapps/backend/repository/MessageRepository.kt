package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.chat.MessageEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<MessageEntity, String> {

    fun findAllByConversationIdAndTextContainsOrderByDateTimeDesc(
        conversationId: String,
        search: String,
        pageable: Pageable
    ): Page<MessageEntity>

}