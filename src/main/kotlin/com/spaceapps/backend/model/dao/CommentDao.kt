package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table
class CommentDao(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Column(name = "userId")
        var userId: Int = 0,
        @Column(name = "postId")
        var postId: Int = 0,
        @Column(name = "text")
        var text: String? = null
)