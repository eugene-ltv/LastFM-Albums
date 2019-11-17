package com.saiferwp.lastfmalbums.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saiferwp.lastfmalbums.data.db.entity.AlbumInfoEntity
import com.saiferwp.lastfmalbums.data.db.entity.TrackInfoEntity

@Dao
interface AlbumInfoDao {

    @Query("SELECT * FROM album_info")
    fun getAllAlbumsAsync(): LiveData<List<AlbumInfoEntity>>

    @Query("SELECT * FROM album_info")
    fun getAllAlbums(): List<AlbumInfoEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAlbumAndTracks(album: AlbumInfoEntity, tracks: List<TrackInfoEntity>)

    @Delete
    fun deleteAlbum(album: AlbumInfoEntity)
}