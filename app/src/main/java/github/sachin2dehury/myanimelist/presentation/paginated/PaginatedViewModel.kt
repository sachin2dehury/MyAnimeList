package github.sachin2dehury.myanimelist.presentation.paginated

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.myanimelist.domain.usecase.PaginatedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PaginatedViewModel @Inject constructor(private val useCase: PaginatedUseCase) : ViewModel() {

    private val pagingConfig = PagingConfig(8)

    private val _state = MutableStateFlow(PaginatedUiState())
    val state = _state.asStateFlow()

    fun updateState(sortingOrder: String? = null, query: String? = null) {
        _state.update {
            it.copy(sortingOrder ?: it.sortingOrder, query ?: it.query)
        }
    }

    val pager = _state.flatMapLatest {
        Pager(pagingConfig, 1) {
            AnimePagingSource(useCase, it.query, it.sortingOrder)
        }.flow
    }
}
