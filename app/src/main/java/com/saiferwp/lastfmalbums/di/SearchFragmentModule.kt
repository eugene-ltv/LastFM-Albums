package com.saiferwp.lastfmalbums.di

import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.ui.search.SearchFragment
import com.saiferwp.lastfmalbums.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class SearchFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun searchFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchFragmentViewModel(viewModel: SearchViewModel): ViewModel
}
