package com.spaceapps.backend.model.dao.feeds

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "feeds")
data class FeedEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var created: LocalDateTime = LocalDateTime.now(),
    var title: String = "",
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id")
    var items: MutableList<FeedItemEntity> = mutableListOf()
)