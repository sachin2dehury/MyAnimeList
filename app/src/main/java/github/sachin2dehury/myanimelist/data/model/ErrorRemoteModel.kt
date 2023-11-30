package github.sachin2dehury.myanimelist.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorRemoteModel(
    val error: String? = null,
    val messages: Messages? = null,
    val status: Int? = null,
    val type: String? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Messages(
        val page: List<String?>? = null,
    )
}
