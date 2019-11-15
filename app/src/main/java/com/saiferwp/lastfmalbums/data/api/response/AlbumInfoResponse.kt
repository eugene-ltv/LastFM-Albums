package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo

data class AlbumInfoResponse(
    @SerializedName("album") val albumInfo: AlbumInfo
) : Response()