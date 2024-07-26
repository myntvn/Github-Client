package com.mynt.app.githubclient.network.model

import com.google.gson.annotations.SerializedName
import com.mynt.app.githubclient.model.Repo

data class RepoResponse(
    val id: String,
    val name: String,
    val description: String?,
    val language: String?,
    val fork: Boolean,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("stargazers_count") val stars: Int?
)

fun RepoResponse.toDomainModel(): Repo {
    return Repo(
        id = this.id,
        name = this.name,
        description= this.description.orEmpty(),
        language = this.language.orEmpty(),
        stars = this.stars ?: 0,
        url = this.htmlUrl.orEmpty()
    )
}
