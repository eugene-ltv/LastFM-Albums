package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName

data class AlbumInfoResponse(
    @SerializedName("album") val albumInfoModel: AlbumInfoModel
) : Response()

data class AlbumInfoModel(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("mbid") val mbId: String,
    @SerializedName("image") val images: List<Image>,
    @SerializedName("tracks") val tracks: Tracks
) {
    data class Image(
        @SerializedName("#text") val link: String
    )

    data class Tracks(
        @SerializedName("track") val list: List<TrackInfoModel>
    )

    fun getCoverUrl(): String {
        return images.last().link
    }
}

data class TrackInfoModel(
    @SerializedName("name") val name: String,
    @SerializedName("duration") val duration: Long
)