package com.saiferwp.lastfmalbums.domain.model

typealias AlbumMbId = String

data class Album(
    val mbId: AlbumMbId,
    val name: String,
    val coverUrl: String
)