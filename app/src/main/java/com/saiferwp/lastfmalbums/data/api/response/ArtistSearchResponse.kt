package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName

data class ArtistSearchResponse(
    @SerializedName("results") val data: Results
) : Response() {
    data class Results(
        @SerializedName("opensearch:totalResults") val totalResults: Int,
        @SerializedName("artistmatches") val artistmatches: Artistmatches
    ) {
        data class Artistmatches (
            @SerializedName("artist") val artist: List<ArtistModel>
        )
    }
}

data class ArtistModel(
    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbId: String
)