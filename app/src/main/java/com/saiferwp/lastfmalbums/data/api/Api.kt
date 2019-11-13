package com.saiferwp.lastfmalbums.data.api

import com.saiferwp.lastfmalbums.data.api.response.ArtistSearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?method=artist.search&format=json")
    fun getArtistSearchAsync(
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): Deferred<Response<ArtistSearchResponse>>
}