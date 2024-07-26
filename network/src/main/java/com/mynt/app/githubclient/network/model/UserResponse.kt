package com.mynt.app.githubclient.network.model

import com.google.gson.annotations.SerializedName
import com.mynt.app.githubclient.model.User

data class SearchUserResponse(
    val items: List<SimpleUserResponse>
)

data class SimpleUserResponse(
    val login: String,
    val id: String,
    @SerializedName("avatar_url") val avatar: String,
)

data class DetailUserResponse(
    val login: String,
    val id: String,
    val name: String,
    @SerializedName("avatar_url") val avatar: String,
    val followers: Int,
    val following: Int,
    @SerializedName("repos_url") val reposUrl: String
)

fun SimpleUserResponse.toDomainModel(): User {
    return User(
        id = this.login,
        name = login,
        avatar = this.avatar,
        followers = 0,
        following = 0
    )
}

fun DetailUserResponse.toDomainModel(): User {
    return User(
        id = this.login,
        name = this.name,
        avatar = this.avatar,
        followers = this.followers,
        following = this.following
    )
}
