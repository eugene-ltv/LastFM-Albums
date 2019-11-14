package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName
import com.saiferwp.lastfmalbums.domain.model.Album

data class TopAlbumsResponse(
    @SerializedName("topalbums") val result: Result
) : Response() {
    data class Result(
        @SerializedName("album") val albums: List<Album>
    )
}

