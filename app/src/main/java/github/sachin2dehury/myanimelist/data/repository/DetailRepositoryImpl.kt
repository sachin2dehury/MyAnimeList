package github.sachin2dehury.myanimelist.data.repository

import github.sachin2dehury.myanimelist.data.AnimeService

class DetailRepositoryImpl(private val service: AnimeService) : DetailRepository {
    override suspend fun getAnime(id: Int) = service.getAnime(id)
}
