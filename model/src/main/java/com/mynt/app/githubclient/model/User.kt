package com.mynt.app.githubclient.model

data class User(
    val id: String,
    val name: String,
    val avatar: String,
    val followers: Int,
    val following: Int
)
