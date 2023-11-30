package github.sachin2dehury.myanimelist.data.repository

import github.sachin2dehury.myanimelist.data.AnimeService

class PaginatedRepositoryImpl(private val service: AnimeService) : PaginatedRepository {
    override suspend fun getPaginatedAnime(page: Int, limit: Int) =
        service.getPaginatedAnime(page, limit)
}
