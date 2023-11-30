package github.sachin2dehury.myanimelist.domain.model

data class PaginatedModel(
    val aired: String,
    val duration: String,
    val episodes: Int,
    val images: String,
    val malId: Int,
    val rank: Int,
    val rating: String,
    val score: Int,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
)
