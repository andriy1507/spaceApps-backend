package com.spaceapps.backend.model.dao.feeds

import javax.persistence.*

@Entity
@Table(name = "feed_items")
class FeedItemEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "text")
    var text: String? = null,
    @Column(name = "image_url")
    var imageUrl: String? = null,
    @Column(name = "type")
    var type: FeedItemType = FeedItemType.Image
)