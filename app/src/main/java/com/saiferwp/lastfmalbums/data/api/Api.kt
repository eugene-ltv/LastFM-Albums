package com.saiferwp.lastfmalbums.data.api

import com.saiferwp.lastfmalbums.data.api.response.ArtistSearchResponse
import com.saiferwp.lastfmalbums.data.api.response.TopAlbumsResponse
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?method=artist.search&format=json")
    fun getArtistSearchAsync(
        @Query("artist") artist: String,
        @Query("page") page: Int? = 1
    ): Deferred<Response<ArtistSearchResponse>>

    @GET("?method=artist.gettopalbums&format=json")
    fun getTopAlbumsAsync(
        @Query("mbid") mbid: ArtistMbId,
        @Query("page") page: Int? = 1
    ): Deferred<Response<TopAlbumsResponse>>
}