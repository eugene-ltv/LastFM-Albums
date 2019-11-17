package com.saiferwp.lastfmalbums.data.db.entity

import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.TrackInfo
import java.util.*


fun AlbumInfo.toAlbumInfoEntity(): AlbumInfoEntity {
    return AlbumInfoEntity(
        albumMbId = mbId,
        name = name,
        artist = artist,
        coverUrl = coverUrl
    )
}

fun AlbumInfoEntity.toAlbumInfo(tracks: List<TrackInfo>): AlbumInfo {
    return AlbumInfo(
        mbId = albumMbId,
        name = name,
        artist = artist,
        coverUrl = coverUrl,
        tracks = tracks
    )
}

fun TrackInfo.toTrackInfoEntity(albumMbId: String): TrackInfoEntity {
    return TrackInfoEntity(
        UUID.randomUUID().toString(),
        name = name,
        duration = duration,
        albumMbId = albumMbId
    )
}

fun TrackInfoEntity.toTrackInfo(): TrackInfo {
    return TrackInfo(
        name = name,
        duration = duration
    )
}