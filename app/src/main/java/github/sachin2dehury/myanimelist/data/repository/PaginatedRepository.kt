package github.sachin2dehury.myanimelist.data.repository

import github.sachin2dehury.myanimelist.data.model.PaginatedRemoteModel
import retrofit2.Response

interface PaginatedRepository {
    suspend fun getPaginatedAnime(page: Int, limit: Int): Response<PaginatedRemoteModel>
}
