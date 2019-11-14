package com.saiferwp.lastfmalbums.di

import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.ui.topalbums.TopAlbumsFragment
import com.saiferwp.lastfmalbums.ui.topalbums.TopAlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class TopAlbumsFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun topAlbumsFragment(): TopAlbumsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopAlbumsViewModel::class)
    abstract fun bindTopAlbumsFragmentViewModel(viewModel: TopAlbumsViewModel): ViewModel
}
