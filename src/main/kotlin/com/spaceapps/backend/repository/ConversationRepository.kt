package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.chat.ConversationEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConversationRepository : CrudRepository<ConversationEntity, String> {

    fun findAllByNameContains(search: String, pageable: Pageable): Page<ConversationEntity>

}