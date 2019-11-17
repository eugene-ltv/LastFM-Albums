package com.saiferwp.lastfmalbums.domain.albuminfo

import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId
import javax.inject.Inject

class IsAlbumSavedUseCase @Inject constructor(
    private val savedAlbumsRepository: SavedAlbumsRepository
) : UseCase<AlbumMbId, Boolean>() {

    override suspend fun execute(parameters: AlbumMbId): Boolean {
        return savedAlbumsRepository.isAlbumSaved(parameters)
    }
}