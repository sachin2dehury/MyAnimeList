package github.sachin2dehury.myanimelist.domain.usecase

import androidx.paging.PagingSource
import com.squareup.moshi.Moshi
import github.sachin2dehury.myanimelist.data.model.ErrorRemoteModel
import github.sachin2dehury.myanimelist.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel
import github.sachin2dehury.myanimelist.domain.toPaginatedModel

class PaginatedUseCase(private val repository: PaginatedRepository, private val moshi: Moshi) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
    ): PagingSource.LoadResult<Int, PaginatedModel> {
        val response = repository.getPaginatedAnime(page, limit)
        return if (response.isSuccessful) {
            val nextPage =
                if (response.body()?.pagination?.hasNextPage == true) page + 1 else null
            PagingSource.LoadResult.Page(
                response.body()?.data.orEmpty().filterNotNull().map { it.toPaginatedModel() },
                null,
                nextPage,
            )
        } else {
            val body = moshi.adapter(ErrorRemoteModel::class.java)
                .fromJson(response.errorBody()?.string().orEmpty())
            PagingSource.LoadResult.Error(Throwable(body?.error.orEmpty()))
        }
    }
}
