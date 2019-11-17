package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("topalbums") val result: Result
) : Response() {
    data class Result(
        @SerializedName("album") val albums: List<TopAlbumModel>
    )
}

data class TopAlbumModel(
    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbId: String?,
    @SerializedName("image") val images: List<Image>
) {
    data class Image(
        @SerializedName("#text") val link: String
    )

    fun getCoverUrl(): String {
        return images.last().link
    }
}