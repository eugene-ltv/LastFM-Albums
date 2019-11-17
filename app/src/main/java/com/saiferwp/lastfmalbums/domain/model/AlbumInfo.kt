package com.saiferwp.lastfmalbums.domain.model

data class AlbumInfo(
    val mbId: AlbumMbId,
    val name: String,
    val artist: String,
    val coverUrl: String,
    val tracks: List<TrackInfo>
)

data class TrackInfo(
    val name: String,
    val duration: Long
)