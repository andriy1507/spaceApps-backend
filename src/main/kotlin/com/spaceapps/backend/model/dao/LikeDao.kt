package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table
class LikeDao(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Column(name = "userId")
        var userId: Long = 0,
        @Column(name = "postId")
        var postId: Long = 0
)