package com.saiferwp.lastfmalbums.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiferwp.lastfmalbums.domain.model.Artist
import com.saiferwp.lastfmalbums.domain.search.SearchArtistsUseCase
import com.saiferwp.lastfmalbums.util.Result
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModel() {

    val liveData = MutableLiveData<Result<List<Artist>>>()

    fun search(s: String) {
        searchArtistsUseCase.invoke(s, liveData)
    }
}
