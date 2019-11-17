package com.saiferwp.lastfmalbums.domain.albuminfo

import com.saiferwp.lastfmalbums.data.SavedAlbumsRepository
import com.saiferwp.lastfmalbums.domain.UseCase
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo
import com.saiferwp.lastfmalbums.domain.model.AlbumMbId
import javax.inject.Inject

class AlbumInfoFromDBUseCase @Inject constructor(
    private val savedAlbumsRepository: SavedAlbumsRepository
) : UseCase<AlbumMbId, AlbumInfo>() {
    override suspend fun execute(parameters: AlbumMbId): AlbumInfo {
        return savedAlbumsRepository.getSavedAlbum(parameters)
            ?: throw IllegalStateException("Album with id $parameters missing in DB")
    }
}