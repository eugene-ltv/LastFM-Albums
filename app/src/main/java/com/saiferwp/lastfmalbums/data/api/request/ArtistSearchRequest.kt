package com.saiferwp.lastfmalbums.data.api.request

import com.saiferwp.lastfmalbums.data.api.Api
import com.saiferwp.lastfmalbums.data.api.response.ArtistSearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class ArtistSearchRequest(private val artist: String, private val currentPage: Int = 1) : Request<ArtistSearchResponse>() {
    override fun executeAsync(api: Api): Deferred<Response<ArtistSearchResponse>> {
        return api.getArtistSearchAsync(artist, currentPage)
    }
}