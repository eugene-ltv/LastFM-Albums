package com.saiferwp.lastfmalbums.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.saiferwp.lastfmalbums.data.db.entity.TrackInfoEntity
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId

@Dao
interface TrackInfoDao {

    @Query("SELECT * FROM track_info WHERE albumMbId = :albumMbId")
    fun getAlbumTracksAsync(albumMbId: AlbumMbId): LiveData<List<TrackInfoEntity>>

    @Query("SELECT * FROM track_info WHERE albumMbId = :albumMbId")
    fun getAlbumTracks(albumMbId: AlbumMbId): List<TrackInfoEntity>
}