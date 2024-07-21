package com.mynt.app.githubclient.network.model

import com.google.gson.annotations.SerializedName
import com.mynt.app.githubclient.model.User

data class UsersResponse(
    val items: List<UserResponse>
)

data class UserResponse(
    val login: String,
    val id: String,
    @SerializedName("avatar_url") val avatar: String
)

fun UserResponse.toDomainModel(): User {
    return User(
        id = this.id,
        name = this.login,
        avatar = this.avatar
    )
}
