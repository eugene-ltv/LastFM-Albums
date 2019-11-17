package com.saiferwp.lastfmalbums.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId

@Entity(tableName = "album_info")
data class AlbumInfoEntity(
    @PrimaryKey val albumMbId: AlbumMbId,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "artist") val artist: String,
    @ColumnInfo(name = "coverUrl") val coverUrl: String?
)