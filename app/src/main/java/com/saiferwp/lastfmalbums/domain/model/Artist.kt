package com.saiferwp.lastfmalbums.domain.model

typealias ArtistMbId = String

data class Artist(
    val mbId: ArtistMbId,
    val name: String
)