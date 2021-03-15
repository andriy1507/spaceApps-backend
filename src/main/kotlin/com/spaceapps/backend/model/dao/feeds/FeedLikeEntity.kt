package com.spaceapps.backend.model.dao.feeds

import javax.persistence.*

@Entity
@Table(name = "feed_likes")
data class FeedLikeEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "feed_id")
    var feedId: Int = 0,
    @Column(name = "user_id")
    var userId: Int = 0
)