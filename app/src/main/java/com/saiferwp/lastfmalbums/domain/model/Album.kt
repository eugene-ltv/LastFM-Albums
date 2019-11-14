package com.saiferwp.lastfmalbums.domain.model

import com.google.gson.annotations.SerializedName

typealias AlbumMbId = String

data class Album(
    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbId: AlbumMbId,
    @SerializedName("image") val images: List<Image>
) {
    data class Image(
        @SerializedName("#text") val link: String
    )

    fun getPictureUrl(): String {
        return images.last().link
    }
}