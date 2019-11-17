package com.saiferwp.lastfmalbums.di

import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.ui.savedalbums.SavedAlbumsFragment
import com.saiferwp.lastfmalbums.ui.savedalbums.SavedAlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class SavedAlbumsFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun savedAlbumsFragment(): SavedAlbumsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SavedAlbumsViewModel::class)
    abstract fun bindSavedAlbumsFragmentViewModel(viewModel: SavedAlbumsViewModel): ViewModel
}