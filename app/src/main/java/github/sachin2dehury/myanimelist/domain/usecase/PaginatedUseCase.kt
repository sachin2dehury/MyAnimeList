package github.sachin2dehury.myanimelist.domain.usecase

import androidx.paging.PagingSource
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

interface PaginatedUseCase {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        query: String?,
        sortingOrder: String?,
    ): PagingSource.LoadResult<Int, PaginatedModel>
}
