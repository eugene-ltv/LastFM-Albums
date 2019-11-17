package com.saiferwp.lastfmalbums.domain.albuminfo

import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import javax.inject.Inject

class SaveAlbumUseCase @Inject constructor(
    private val savedAlbumsRepository: SavedAlbumsRepository
) : UseCase<AlbumInfo, Unit>() {

    override suspend fun execute(parameters: AlbumInfo) {
        savedAlbumsRepository.saveAlbum(parameters)
    }
}