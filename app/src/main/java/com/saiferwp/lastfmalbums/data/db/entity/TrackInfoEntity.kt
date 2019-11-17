package com.saiferwp.lastfmalbums.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId

@Entity(
    tableName = "track_info",
    foreignKeys = [ForeignKey(
        entity = AlbumInfoEntity::class,
        parentColumns = ["albumMbId"],
        childColumns = ["albumMbId"],
        onDelete = CASCADE
    )]
)
data class TrackInfoEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "duration") val duration: Long,
    @ColumnInfo(name = "albumMbId") val albumMbId: AlbumMbId
)