package github.sachin2dehury.myanimelist.domain.usecase

import androidx.paging.PagingSource
import github.sachin2dehury.myanimelist.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelist.data.toPaginatedModel
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

class PaginatedUseCaseImpl(private val repository: PaginatedRepository) : PaginatedUseCase {
    override suspend operator fun invoke(
        page: Int,
        limit: Int,
        query: String?,
        sortingOrder: String?,
    ): PagingSource.LoadResult<Int, PaginatedModel> {
        val response = repository.getPaginatedAnime(page, limit, query, sortingOrder)
        return if (response.isSuccessful && response.body()?.error.isNullOrEmpty()) {
            val nextPage = if (response.body()?.pagination?.hasNextPage == true) page + 1 else null
            PagingSource.LoadResult.Page(
                response.body()?.data.orEmpty().filterNotNull().map { it.toPaginatedModel() },
                null,
                nextPage,
            )
        } else {
            PagingSource.LoadResult.Error(
                Throwable(
                    response.body()?.messages?.values?.joinToString { it?.firstOrNull().orEmpty() }
                        ?: "Something went wrong!!",
                ),
            )
        }
    }
}
