package com.saiferwp.lastfmalbums.ui.topalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.Album
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import com.saiferwp.lastfmalbums.domain.topalbums.TopAlbumsParameters
import com.saiferwp.lastfmalbums.domain.topalbums.TopAlbumsUseCase
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.succeeded
import com.saiferwp.lastfmalbums.util.successOr
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(
    private val topAlbumsUseCase: TopAlbumsUseCase
) : ViewModel(), TopAlbumsEventListener {

    private val _topAlbums = MediatorLiveData<Result<List<Album>>>()
    val topAlbums: LiveData<Result<List<Album>>>
        get() = _topAlbums

    private val _openAlbumDetailsAction = MutableLiveData<Album>()
    val openAlbumDetailsAction: LiveData<Album>
        get() = _openAlbumDetailsAction

    private var artistMbId: ArtistMbId? = null

    private var currentPage = 1
    var isLastPage = false
    var isLoading = false

    var list: List<Album> = emptyList()

    fun loadTopAlbums(mbId: ArtistMbId) {
        isLoading = true
        val topAlbums = MutableLiveData<Result<List<Album>>>()

        _topAlbums.addSource(topAlbums) {
            if (it is Result.Loading) {
                if (currentPage == 1) {
                    _topAlbums.value = it
                }
                return@addSource
            }

            if (it.succeeded) {
                isLoading = false

                val newList = it.successOr(emptyList())
                isLastPage = newList.isEmpty()

                list = list.plus(newList)

                _topAlbums.value = Result.Success(list)
            } else {
                _topAlbums.value = it
            }
        }

        topAlbumsUseCase.invoke(TopAlbumsParameters(mbId, currentPage), topAlbums)
        artistMbId = mbId
    }

    override fun openAlbumDetails(album: Album) {
        _openAlbumDetailsAction.value = album
    }

    fun loadMoreItems() {
        currentPage++
        artistMbId?.let {
            loadTopAlbums(it)
        }
    }
}

interface TopAlbumsEventListener {
    fun openAlbumDetails(album: Album)
}