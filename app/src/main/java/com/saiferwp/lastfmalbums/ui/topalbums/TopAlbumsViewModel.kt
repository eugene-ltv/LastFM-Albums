package com.saiferwp.lastfmalbums.ui.topalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.Album
import com.saiferwp.lastfmalbums.domain.model.ArtistMbId
import com.saiferwp.lastfmalbums.domain.topalbums.TopAlbumsParameters
import com.saiferwp.lastfmalbums.domain.topalbums.TopAlbumsUseCase
import com.saiferwp.lastfmalbums.util.Result
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(
    private val topAlbumsUseCase: TopAlbumsUseCase
) : ViewModel(), TopAlbumsEventListener {

    private val _topAlbums = MutableLiveData<Result<List<Album>>>()
    val topAlbums: LiveData<Result<List<Album>>>
        get() = _topAlbums

    private val _openAlbumDetailsAction = MutableLiveData<Album>()
    val openAlbumDetailsAction: LiveData<Album>
        get() = _openAlbumDetailsAction

    fun loadTopAlbums(mbId: ArtistMbId) {
        topAlbumsUseCase.invoke(TopAlbumsParameters(mbId, 1), _topAlbums)
    }

    override fun openAlbumDetails(album: Album) {
        _openAlbumDetailsAction.value = album
    }
}

interface TopAlbumsEventListener {
    fun openAlbumDetails(album: Album)
}