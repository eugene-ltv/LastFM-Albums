package com.saiferwp.lastfmalbums.di

import android.content.Context
import com.saiferwp.lastfmalbums.App
import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.ApiFactory
import com.saiferwp.lastfmalbums.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        val factory = ApiFactory()
        val api = factory.create()
        return ApiClient(api)
    }

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun provideSavedAlbumsRepository(appDatabase: AppDatabase): SavedAlbumsRepository =
        SavedAlbumsRepository(appDatabase)
}
