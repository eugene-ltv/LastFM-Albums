package com.saiferwp.lastfmalbums.domain.search

import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.request.ArtistSearchRequest
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.Artist
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(
    private val api: ApiClient
) : UseCase<String, List<Artist>>() {

    override suspend fun execute(parameters: String): List<Artist> {
        val request = ArtistSearchRequest(parameters)

        val execute = api.executeAsync(request)
        val response = execute.await()

        return if (response.isSuccessful) {
            if (response.body() != null) {
                response.body()!!.data.artistmatches.artist
            } else {
                throw RuntimeException(response.message())
            }
        } else {
            throw RuntimeException(response.message())
        }
    }
}