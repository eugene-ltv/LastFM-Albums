package com.saiferwp.lastfmalbums.domain.albuminfo

import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.request.AlbumInfoRequest
import com.saiferwp.lastfmalbums.data.api.request.Request
import com.saiferwp.lastfmalbums.data.api.response.AlbumInfoResponse
import com.saiferwp.lastfmalbums.domain.LoadFromApiUseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import javax.inject.Inject

class AlbumInfoUseCase @Inject constructor(
    api: ApiClient
) : LoadFromApiUseCase<AlbumInfoParameters, AlbumInfo, AlbumInfoResponse>(api) {

    override fun prepareRequest(parameters: AlbumInfoParameters): Request<AlbumInfoResponse> {
        return AlbumInfoRequest(parameters.mbId)
    }

    override fun getResponseData(body: AlbumInfoResponse): AlbumInfo {
        return body.albumInfo
    }
}

data class AlbumInfoParameters(
    val mbId: ArtistMbId
)