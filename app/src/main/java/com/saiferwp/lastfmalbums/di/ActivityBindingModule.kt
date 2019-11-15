package com.saiferwp.lastfmalbums.di

import com.saiferwp.lastfmalbums.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // fragments
            SearchFragmentModule::class,
            TopAlbumsFragmentModule::class,
            AlbumInfoFragmentModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
