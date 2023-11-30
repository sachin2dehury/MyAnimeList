package github.sachin2dehury.myanimelist.presentation.paginated

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.myanimelist.domain.usecase.PaginatedUseCase
import javax.inject.Inject

@HiltViewModel
class PaginatedViewModel @Inject constructor(private val useCase: PaginatedUseCase) : ViewModel() {

    private val pagingConfig = PagingConfig(5)

    val pager = Pager(pagingConfig, 1) {
        AnimePagingSource(useCase)
    }.flow
}
