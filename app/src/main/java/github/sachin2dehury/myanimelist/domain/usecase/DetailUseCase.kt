package github.sachin2dehury.myanimelist.domain.usecase

import com.squareup.moshi.Moshi
import github.sachin2dehury.myanimelist.data.model.ErrorRemoteModel
import github.sachin2dehury.myanimelist.data.repository.DetailRepository
import github.sachin2dehury.myanimelist.domain.ResultType
import github.sachin2dehury.myanimelist.domain.toDetailModel
import kotlinx.coroutines.flow.flow

class DetailUseCase(private val detailRepository: DetailRepository) {

    suspend operator fun invoke(id: Int) = flow {
        emit(ResultType.Loading)
        val response = detailRepository.getAnime(id)
        if (response.isSuccessful) {
            emit(ResultType.Success(response.body()?.data?.toDetailModel()))
        } else {
            val body = Moshi.Builder().build().adapter(ErrorRemoteModel::class.java)
                .fromJson(response.errorBody()?.string().orEmpty())
            emit(ResultType.Error(body?.error.orEmpty()))
        }
    }
}
