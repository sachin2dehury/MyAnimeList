package github.sachin2dehury.myanimelist.domain

sealed interface ResultType<out T> {
    data object Loading : ResultType<Nothing>
    data class Success<T>(val data: T) : ResultType<T>
    data class Error(val message: String) : ResultType<Nothing>
}
