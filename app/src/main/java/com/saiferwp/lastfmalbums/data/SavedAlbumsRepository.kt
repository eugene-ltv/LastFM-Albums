package com.saiferwp.lastfmalbums.data

import com.saiferwp.lastfmalbums.data.db.AppDatabase
import com.saiferwp.lastfmalbums.data.db.entity.toAlbumInfo
import com.saiferwp.lastfmalbums.data.db.entity.toAlbumInfoEntity
import com.saiferwp.lastfmalbums.data.db.entity.toTrackInfo
import com.saiferwp.lastfmalbums.data.db.entity.toTrackInfoEntity
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId

class SavedAlbumsRepository(
    private val appDatabase: AppDatabase
) {
    fun isAlbumSaved(albumMbId: AlbumMbId) : Boolean {
        return appDatabase.albumInfoDao().getAlbum(albumMbId) != null
    }

    fun saveAlbum(albumInfo: AlbumInfo) {
        val albumInfoEntity = albumInfo.toAlbumInfoEntity()
        val trackInfoEntityList = albumInfo.tracks.map {
            it.toTrackInfoEntity(albumInfoEntity.albumMbId)
        }
        appDatabase.albumInfoDao().insertAlbumAndTracks(albumInfoEntity, trackInfoEntityList)
    }

    fun getSavedAlbums(): List<AlbumInfo> {
        val savedAlbums = appDatabase.albumInfoDao().getAllAlbums()

        return savedAlbums
            .map { album ->
                album.toAlbumInfo(emptyList())
            }
    }

    fun getSavedAlbum(albumMbId: AlbumMbId): AlbumInfo? {
        val savedAlbum = appDatabase.albumInfoDao().getAlbum(albumMbId)

        val tracks = appDatabase.trackInfoDao().getAlbumTracks(albumMbId)
            .map { track ->
                track.toTrackInfo()
            }
        return savedAlbum?.toAlbumInfo(tracks)
    }

    fun deleteAlbum(albumInfo: AlbumInfo) {
        val albumInfoEntity = albumInfo.toAlbumInfoEntity()
        appDatabase.albumInfoDao().deleteAlbum(albumInfoEntity)
    }
}