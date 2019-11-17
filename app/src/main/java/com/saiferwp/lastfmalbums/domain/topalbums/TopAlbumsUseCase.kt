package com.saiferwp.lastfmalbums.domain.topalbums

import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.request.Request
import com.saiferwp.lastfmalbums.data.api.request.TopAlbumsRequest
import com.saiferwp.lastfmalbums.data.api.response.TopAlbumsResponse
import com.saiferwp.lastfmalbums.data.api.response.toAlbum
import com.saiferwp.lastfmalbums.domain.LoadFromApiUseCase
import com.saiferwp.lastfmalbums.domain.model.Album
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import javax.inject.Inject

class TopAlbumsUseCase @Inject constructor(
    api: ApiClient
) : LoadFromApiUseCase<TopAlbumsParameters, List<Album>, TopAlbumsResponse>(api) {

    override fun prepareRequest(parameters: TopAlbumsParameters): Request<TopAlbumsResponse> {
        return TopAlbumsRequest(parameters.mbId, parameters.page)
    }

    override fun getResponseData(body: TopAlbumsResponse): List<Album> {
        return body.result.albums
            // remove albums without IDs, they break following logic
            .map { if (it.mbId != null) it else null }
            .mapNotNull { it?.toAlbum() }
    }
}

data class TopAlbumsParameters(
    val mbId: ArtistMbId,
    val page: Int?
)