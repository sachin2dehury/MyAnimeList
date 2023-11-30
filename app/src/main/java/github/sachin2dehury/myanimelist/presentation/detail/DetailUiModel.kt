package github.sachin2dehury.myanimelist.presentation.detail

import github.sachin2dehury.myanimelist.domain.model.DetailModel

data class DetailUiModel(
    val isLoading: Boolean = false,
    val data: DetailModel? = null,
    val error: String? = null,
)
