package com.mynt.app.githubclient.model

data class Repo(
    val id: String,
    val name: String,
    val description: String,
    val language: String,
    val stars: Int,
    val url: String
)