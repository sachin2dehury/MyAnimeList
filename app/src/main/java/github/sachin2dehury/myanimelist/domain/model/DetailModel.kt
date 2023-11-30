package github.sachin2dehury.myanimelist.domain.model

data class DetailModel(
    val aired: String,
    val background: String,
    val duration: String,
    val endingTheme: List<String>,
    val episodes: Int,
    val genres: List<String>,
    val images: String,
    val malId: Int,
    val openingTheme: List<String>,
    val rank: Int,
    val rating: String,
    val score: Float,
    val streaming: String,
    val synopsis: String,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
    val trailer: String,
)
