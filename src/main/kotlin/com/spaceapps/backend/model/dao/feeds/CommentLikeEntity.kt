package com.spaceapps.backend.model.dao.feeds

import javax.persistence.*

@Entity
@Table(name = "comment_likes")
class CommentLikeEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(name = "comment_id")
    var commentId: Int = 0,
    @Column(name = "user_id")
    var userId: Int = 0
)