package com.saiferwp.lastfmalbums.domain.model

import com.google.gson.annotations.SerializedName

data class AlbumInfo(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("mbid") val mbid: AlbumMbId,
    @SerializedName("image") val images: List<Image>,
    @SerializedName("tracks") val tracks: Tracks
) {
    data class Image(
        @SerializedName("#text") val link: String
    )

    data class Tracks(
        @SerializedName("track") val list: List<TrackInfo>
    )

    fun getPictureUrl(): String {
        return images.last().link
    }
}

data class TrackInfo(
    @SerializedName("name") val name: String,
    @SerializedName("duration") val duration: Long
)