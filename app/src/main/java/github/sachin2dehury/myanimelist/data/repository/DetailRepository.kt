package github.sachin2dehury.myanimelist.data.repository

import github.sachin2dehury.myanimelist.data.model.DetailRemoteModel
import retrofit2.Response

interface DetailRepository {
    suspend fun getAnime(id: Int): Response<DetailRemoteModel>
}
