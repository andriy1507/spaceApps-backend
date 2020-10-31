package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity(name = "user_task")
@Table
data class UserTaskDao(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,
        @Column(name = "title")
        var title: String? = null,
        @Column(name = "text")
        var text: String? = null,
        @Column(name = "userId")
        var userId: Int = 0
)