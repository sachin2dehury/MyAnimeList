package github.sachin2dehury.myanimelist.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.sachin2dehury.myanimelist.data.model.DetailRemoteModel
import github.sachin2dehury.myanimelist.data.model.PaginatedRemoteModel
import github.sachin2dehury.myanimelist.domain.model.DetailModel
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus

fun PaginatedRemoteModel.Data.toPaginatedModel() = PaginatedModel(
    aired?.prop?.string.orEmpty(),
    duration.orEmpty(),
    episodes.orZero(),
    images?.webp?.largeImageUrl.orEmpty(),
    malId.orZero(),
    rank.orZero(),
    rating.orEmpty(),
    score.orZero(),
    title.orEmpty(),
    titleEnglish.orEmpty(),
    titleJapanese.orEmpty(),
)

fun DetailRemoteModel.Data.toDetailModel() = DetailModel(
    aired?.prop?.string.orEmpty(),
    background.orEmpty(),
    duration.orEmpty(),
    theme?.endings.orEmpty().filterNotNull(),
    episodes.orZero(),
    genres.orEmpty().map { it?.name.orEmpty() },
    images?.webp?.largeImageUrl.orEmpty(),
    malId.orZero(),
    theme?.openings.orEmpty().filterNotNull(),
    rank.orZero(),
    rating.orEmpty(),
    score.orZero(),
    streaming?.firstOrNull()?.url.orEmpty(),
    synopsis.orEmpty(),
    title.orEmpty(),
    titleEnglish.orEmpty(),
    titleJapanese.orEmpty(),
    trailer?.url.orEmpty(),
)

fun Int?.orZero() = this ?: 0
