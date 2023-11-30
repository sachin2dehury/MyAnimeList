package github.sachin2dehury.myanimelist.presentation.paginated

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    SortingOrder.END_DATE,
    SortingOrder.TITLE,
    SortingOrder.SCORE,
    SortingOrder.START_DATE,
    SortingOrder.EPISODES,
    SortingOrder.RANK,
    SortingOrder.POPULARITY,
)
annotation class SortingOrder {
    companion object {
        const val ID = "mal_id"
        const val TITLE = "title"
        const val START_DATE = "start_date"
        const val END_DATE = "end_date"
        const val EPISODES = "episodes"
        const val SCORE = "score"
        const val RANK = "rank"
        const val POPULARITY = "popularity"
    }
}