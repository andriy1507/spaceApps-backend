package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.chat.ChatEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : CrudRepository<ChatEntity, String> {

    fun findAllByNameContains(search: String, pageable: Pageable): Page<ChatEntity>

}