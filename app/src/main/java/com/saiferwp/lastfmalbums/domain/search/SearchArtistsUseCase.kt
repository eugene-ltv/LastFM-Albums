package com.saiferwp.lastfmalbums.domain.search

import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.request.ArtistSearchRequest
import com.saiferwp.lastfmalbums.data.api.request.Request
import com.saiferwp.lastfmalbums.data.api.response.ArtistSearchResponse
import com.saiferwp.lastfmalbums.data.api.response.toArtist
import com.saiferwp.lastfmalbums.domain.LoadFromApiUseCase
import com.saiferwp.lastfmalbums.domain.model.Artist
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(
    api: ApiClient
) : LoadFromApiUseCase<SearchArtistsParameters, List<Artist>, ArtistSearchResponse>(api) {

    override fun prepareRequest(parameters: SearchArtistsParameters): Request<ArtistSearchResponse> {
        return ArtistSearchRequest(parameters.searchString, parameters.page)
    }

    override fun getResponseData(body: ArtistSearchResponse): List<Artist> {
        return body.data.artistmatches.artist.map { it.toArtist() }
    }
}

data class SearchArtistsParameters(
    val searchString: String,
    val page: Int?
)