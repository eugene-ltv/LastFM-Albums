package com.saiferwp.lastfmalbums.di

import android.content.Context
import com.saiferwp.lastfmalbums.App
import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.ApiFactory
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
}
