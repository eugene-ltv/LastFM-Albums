package com.saiferwp.lastfmalbums.ui.savedalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.savedalbums.SavedAlbumsUseCase
import com.saiferwp.lastfmalbums.util.Result
import javax.inject.Inject

class SavedAlbumsViewModel @Inject constructor(
    private val topAlbumsUseCase: SavedAlbumsUseCase
) : ViewModel(), SavedAlbumsEventListener {

    private val _savedAlbums = MutableLiveData<Result<List<AlbumInfo>>>()
    val savedAlbums: LiveData<Result<List<AlbumInfo>>>
        get() = _savedAlbums

    private val _openAlbumDetailsAction = MutableLiveData<AlbumInfo>()
    val openAlbumDetailsAction: LiveData<AlbumInfo>
        get() = _openAlbumDetailsAction

    fun loadSavedAlbums() {
        topAlbumsUseCase.invoke(Unit, _savedAlbums)
    }

    override fun openAlbumDetails(album: AlbumInfo) {
        _openAlbumDetailsAction.value = album
    }
}

interface SavedAlbumsEventListener {
    fun openAlbumDetails(album: AlbumInfo)
}