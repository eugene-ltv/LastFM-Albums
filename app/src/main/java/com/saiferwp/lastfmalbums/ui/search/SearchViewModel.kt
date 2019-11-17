package com.saiferwp.lastfmalbums.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.Artist
import com.saiferwp.lastfmalbums.domain.search.SearchArtistsParameters
import com.saiferwp.lastfmalbums.domain.search.SearchArtistsUseCase
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.succeeded
import com.saiferwp.lastfmalbums.util.successOr
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModel(), SearchEventListener {

    private val _artists = MediatorLiveData<Result<List<Artist>>>()
    val artists: LiveData<Result<List<Artist>>>
        get() = _artists

    private val _openTopAlbumsAction = MutableLiveData<Artist>()
    val openTopAlbumsAction: LiveData<Artist>
        get() = _openTopAlbumsAction

    private var searchString: String? = null

    private var currentPage = 1
    var isLastPage = false
    var isLoading = false

    var list: List<Artist> = emptyList()

    fun search(s: String) {
        isLoading = true
        val artists = MutableLiveData<Result<List<Artist>>>()
        _artists.addSource(artists) {
            if (it is Result.Loading) {
                if (currentPage == 1) {
                    _artists.value = it
                }
                return@addSource
            }

            if (it.succeeded) {
                isLoading = false

                val newList = it.successOr(emptyList())
                isLastPage = newList.isEmpty()

                list = list.plus(newList)

                _artists.value = Result.Success(list)
            } else {
                _artists.value = it
            }
        }

        searchArtistsUseCase.invoke(SearchArtistsParameters(s, currentPage), artists)
        searchString = s
    }

    override fun openTopAlbums(artist: Artist) {
        _openTopAlbumsAction.value = artist
    }

    fun loadMoreItems() {
        currentPage++
        searchString?.let {
            search(it)
        }
    }
}

interface SearchEventListener {
    fun openTopAlbums(artist: Artist)
}