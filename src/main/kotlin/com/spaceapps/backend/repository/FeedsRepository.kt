package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.feeds.FeedEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedsRepository : CrudRepository<FeedEntity, Int> {

    fun findAllByTitleLike(search:String, pageable: Pageable): Page<FeedEntity>

}