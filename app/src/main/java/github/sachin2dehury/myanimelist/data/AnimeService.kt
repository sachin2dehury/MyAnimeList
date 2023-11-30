package github.sachin2dehury.myanimelist.data

import github.sachin2dehury.myanimelist.data.model.DetailRemoteModel
import github.sachin2dehury.myanimelist.data.model.PaginatedRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("v4/anime")
    suspend fun getPaginatedAnime(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<PaginatedRemoteModel>

    @GET("v4/anime/{id}/full")
    suspend fun getAnime(@Path("id") id: Int): Response<DetailRemoteModel>

    companion object {
        const val BASE_URL = "https://api.jikan.moe/"
    }
}
