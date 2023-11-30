package github.sachin2dehury.myanimelist.domain

import github.sachin2dehury.myanimelist.data.model.DetailRemoteModel
import github.sachin2dehury.myanimelist.data.model.PaginatedRemoteModel
import github.sachin2dehury.myanimelist.domain.model.DetailModel
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

fun PaginatedRemoteModel.Data.toPaginatedModel() = PaginatedModel(
    duration.orEmpty(),
    episodes.orZero(),
    images?.webp?.largeImageUrl.orEmpty(),
    malId.orZero(),
    rank.orZero(),
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
fun Float?.orZero() = this ?: 0f
