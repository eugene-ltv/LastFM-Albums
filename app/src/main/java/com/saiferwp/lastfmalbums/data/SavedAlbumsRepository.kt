package com.saiferwp.lastfmalbums.data

import com.saiferwp.lastfmalbums.data.db.AppDatabase
import com.saiferwp.lastfmalbums.data.db.entity.toAlbumInfoEntity
import com.saiferwp.lastfmalbums.data.db.entity.toTrackInfoEntity
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo

class SavedAlbumsRepository (
    private val appDatabase: AppDatabase
) {
    fun saveAlbum(albumInfo: AlbumInfo) {
        val albumInfoEntity = albumInfo.toAlbumInfoEntity()
        val trackInfoEntityList = albumInfo.tracks.map {
            it.toTrackInfoEntity(albumInfoEntity.albumMbId)
        }
        appDatabase.albumInfoDao().insertAlbumAndTracks(albumInfoEntity, trackInfoEntityList)
    }
}