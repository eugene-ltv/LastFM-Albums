package com.saiferwp.lastfmalbums.domain.model

import com.google.gson.annotations.SerializedName

typealias ArtistMbId = String

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbId: ArtistMbId
)