package com.saiferwp.lastfmalbums.data.api.request

import com.saiferwp.lastfmalbums.data.api.Api
import com.saiferwp.lastfmalbums.data.api.response.AlbumInfoResponse
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId
import kotlinx.coroutines.Deferred
import retrofit2.Response

class AlbumInfoRequest(private val mbId: AlbumMbId) :
    Request<AlbumInfoResponse>() {
    override fun executeAsync(api: Api): Deferred<Response<AlbumInfoResponse>> {
        return api.getAlbumDetailsAsync(mbId)
    }
}