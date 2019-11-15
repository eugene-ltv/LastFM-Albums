package com.saiferwp.lastfmalbums.ui.albuminfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.albuminfo.AlbumInfoParameters
import com.saiferwp.lastfmalbums.domain.albuminfo.AlbumInfoUseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.util.Result
import javax.inject.Inject

class AlbumInfoViewModel @Inject constructor(
    private val albumInfoUseCase: AlbumInfoUseCase
) : ViewModel() {

    private val _albumInfo = MutableLiveData<Result<AlbumInfo>>()
    val albumInfo: LiveData<Result<AlbumInfo>>
        get() = _albumInfo

    fun loadAlbumInfo(s: String) {
        albumInfoUseCase.invoke(AlbumInfoParameters(s), _albumInfo)
    }
}