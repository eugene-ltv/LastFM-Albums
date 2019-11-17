package com.saiferwp.lastfmalbums.ui.albuminfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.albuminfo.*
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.successOr
import javax.inject.Inject

class AlbumInfoViewModel @Inject constructor(
    private val albumInfoFromApiUseCase: AlbumInfoFromApiUseCase,
    private val albumInfoFromDBUseCase: AlbumInfoFromDBUseCase,
    private val isAlbumSavedUseCase: IsAlbumSavedUseCase,
    private val saveAlbumInfoUseCase: SaveAlbumInfoUseCase,
    private val deleteAlbumInfoUseCase: DeleteAlbumInfoUseCase
) : ViewModel() {

    private val _albumInfo = MutableLiveData<Result<AlbumInfo>>()
    val albumInfo: LiveData<Result<AlbumInfo>>
        get() = _albumInfo

    private val _isAlbumSaved = MediatorLiveData<Result<Boolean>>()
    val isAlbumSaved: LiveData<Result<Boolean>>
        get() = _isAlbumSaved

    fun loadAlbumInfo(albumMbId: AlbumMbId) {
        val isAlbumSaved = MutableLiveData<Result<Boolean>>()
        _isAlbumSaved.addSource(isAlbumSaved) {
            val saved = it.successOr(false)

            if (saved) {
                albumInfoFromDBUseCase.invoke(albumMbId, _albumInfo)
            } else {
                albumInfoFromApiUseCase.invoke(AlbumInfoParameters(albumMbId), _albumInfo)
            }

            _isAlbumSaved.value = it
        }

        isAlbumSavedUseCase.invoke(albumMbId, isAlbumSaved)
    }

    fun saveAlbum(albumInfo: AlbumInfo) {
        saveAlbumInfoUseCase.invoke(albumInfo, _isAlbumSaved)
    }

    fun deleteAlbum(albumInfo: AlbumInfo) {
        deleteAlbumInfoUseCase.invoke(albumInfo, _isAlbumSaved)
    }
}