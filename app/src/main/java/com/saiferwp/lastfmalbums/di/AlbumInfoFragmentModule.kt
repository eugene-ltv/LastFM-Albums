package com.saiferwp.lastfmalbums.di

import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.ui.albuminfo.AlbumInfoFragment
import com.saiferwp.lastfmalbums.ui.albuminfo.AlbumInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class AlbumInfoFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun albumInfoFragment(): AlbumInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(AlbumInfoViewModel::class)
    abstract fun bindAlbumInfoFragmentViewModel(viewModel: AlbumInfoViewModel): ViewModel
}
