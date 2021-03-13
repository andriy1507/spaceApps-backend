package com.spaceapps.backend.model.dao.feeds

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "feeds")
data class FeedEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "created")
    var created: LocalDateTime = LocalDateTime.now(),
    @Column(name = "title")
    var title: String = "",
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id")
    var items: MutableList<FeedItemEntity> = mutableListOf()
)