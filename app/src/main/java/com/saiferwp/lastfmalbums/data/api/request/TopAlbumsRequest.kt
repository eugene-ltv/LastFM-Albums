package com.saiferwp.lastfmalbums.data.api.request

import com.saiferwp.lastfmalbums.data.api.Api
import com.saiferwp.lastfmalbums.data.api.response.TopAlbumsResponse
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import kotlinx.coroutines.Deferred
import retrofit2.Response

class TopAlbumsRequest(private val mbId: ArtistMbId, private val currentPage: Int?) :
    Request<TopAlbumsResponse>() {
    override fun executeAsync(api: Api): Deferred<Response<TopAlbumsResponse>> {
        return api.getTopAlbumsAsync(mbId, currentPage)
    }
}