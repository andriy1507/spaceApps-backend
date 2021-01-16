package com.spaceapps.backend.model.dao

import javax.persistence.*

@Entity
@Table
data class SubscriptionDao(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Column
        val userId: Long = 0,
        @Column
        val subscriberId: Long = 0
)