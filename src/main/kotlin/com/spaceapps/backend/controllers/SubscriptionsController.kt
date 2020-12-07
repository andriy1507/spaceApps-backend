package com.spaceapps.backend.controllers

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("subscriptions")
class SubscriptionsController {

    @PostMapping("/follow/{userId}")
    fun followUser(@PathVariable userId: Long) = Unit

    @DeleteMapping("/unfollow/{userId}")
    fun unfollowUser(@PathVariable userId: Long) = Unit

}