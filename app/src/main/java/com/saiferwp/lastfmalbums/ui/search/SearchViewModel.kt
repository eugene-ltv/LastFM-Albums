package com.saiferwp.lastfmalbums.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.Artist
import com.saiferwp.lastfmalbums.domain.search.SearchArtistsParameters
import com.saiferwp.lastfmalbums.domain.search.SearchArtistsUseCase
import com.saiferwp.lastfmalbums.util.Result
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModel(), SearchEventListener {

    private val _artists = MutableLiveData<Result<List<Artist>>>()
    val artists: LiveData<Result<List<Artist>>>
        get() = _artists

    private val _openTopAlbumsAction = MutableLiveData<Artist>()
    val openTopAlbumsAction: LiveData<Artist>
        get() = _openTopAlbumsAction

    fun search(s: String) {
        searchArtistsUseCase.invoke(SearchArtistsParameters(s, 1), _artists)
    }

    override fun openTopAlbums(artist: Artist) {
        _openTopAlbumsAction.value = artist
    }
}

interface SearchEventListener {
    fun openTopAlbums(artist: Artist)
}