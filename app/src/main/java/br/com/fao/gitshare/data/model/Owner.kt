package br.com.fao.gitshare.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    val login: String,
    @SerializedName(value = "avatar_url")
    val avatarURL: String,
)
