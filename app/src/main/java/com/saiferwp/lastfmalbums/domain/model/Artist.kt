package com.saiferwp.lastfmalbums.domain.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("name") val name: String
)