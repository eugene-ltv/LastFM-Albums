package com.saiferwp.lastfmalbums.domain.albuminfo

import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import javax.inject.Inject

class DeleteAlbumInfoUseCase @Inject constructor(
    private val savedAlbumsRepository: SavedAlbumsRepository
) : UseCase<AlbumInfo, Boolean>() {

    override suspend fun execute(parameters: AlbumInfo): Boolean {
        savedAlbumsRepository.deleteAlbum(parameters)
        return false
    }
}