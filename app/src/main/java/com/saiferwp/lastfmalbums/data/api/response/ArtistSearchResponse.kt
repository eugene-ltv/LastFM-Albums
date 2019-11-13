package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName
import com.saiferwp.lastfmalbums.domain.model.Artist

data class ArtistSearchResponse(
    @SerializedName("results") val data: Results
) : Response() {
    data class Results(
        @SerializedName("opensearch:totalResults") val totalResults: Int,
        @SerializedName("artistmatches") val artistmatches: Artistmatches
    ) {
        data class Artistmatches (
            @SerializedName("artist") val artist: List<Artist>
        )
    }
}

