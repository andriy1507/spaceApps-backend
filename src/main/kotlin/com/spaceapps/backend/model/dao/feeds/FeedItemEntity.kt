package com.spaceapps.backend.model.dao.feeds

import javax.persistence.*

@Entity
@Table(name = "feed_items")
class FeedItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var text: String = ""
)