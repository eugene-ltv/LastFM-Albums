package com.saiferwp.lastfmalbums.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saiferwp.lastfmalbums.data.db.entity.AlbumInfoEntity
import com.saiferwp.lastfmalbums.data.db.entity.TrackInfoEntity


@Database(
    entities = [
        AlbumInfoEntity::class,
        TrackInfoEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumInfoDao(): AlbumInfoDao
    abstract fun trackInfoDao(): TrackInfoDao

    companion object {
        private const val databaseName = "saved_albums"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .build()
        }
    }
}