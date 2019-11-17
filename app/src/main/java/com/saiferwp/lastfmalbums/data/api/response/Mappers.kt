package com.saiferwp.lastfmalbums.data.api.response

import com.saiferwp.lastfmalbums.domain.model.Album
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.Artist
import com.saiferwp.lastfmalbums.domain.model.TrackInfo

fun ArtistModel.toArtist(): Artist {
    return Artist(
        mbId = mbId,
        name = name
    )
}

fun TopAlbumModel.toAlbum(): Album {
    return Album(
        mbId = mbId ?: "",
        name = name,
        coverUrl = getCoverUrl()
    )
}

fun AlbumInfoModel.toAlbumInfo(): AlbumInfo {
    return AlbumInfo(
        mbId = mbId,
        name = name,
        artist = artist,
        coverUrl = getCoverUrl(),
        tracks = tracks.list.map { it.toTrackInfo() }
    )
}

fun TrackInfoModel.toTrackInfo(): TrackInfo {
    return TrackInfo(name, duration)
}