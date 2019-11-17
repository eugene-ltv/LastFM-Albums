package com.saiferwp.lastfmalbums.domain.savedalbums

import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import javax.inject.Inject

class SavedAlbumsUseCase @Inject constructor(
    private val savedAlbumsRepository: SavedAlbumsRepository
) : UseCase<Unit, List<AlbumInfo>>() {

    override suspend fun execute(parameters: Unit): List<AlbumInfo> {
        return savedAlbumsRepository.getSavedAlbums()
    }
}