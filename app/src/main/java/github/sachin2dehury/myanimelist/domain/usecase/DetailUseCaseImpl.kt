package github.sachin2dehury.myanimelist.domain.usecase

import github.sachin2dehury.myanimelist.data.repository.DetailRepository
import github.sachin2dehury.myanimelist.domain.toDetailModel
import github.sachin2dehury.myanimelist.domain.ResultType
import kotlinx.coroutines.flow.flow

class DetailUseCaseImpl(private val detailRepository: DetailRepository) : DetailUseCase {

    override suspend operator fun invoke(id: Int) = flow {
        emit(ResultType.Loading)
        val response = detailRepository.getAnime(id)
        if (response.isSuccessful) {
            emit(ResultType.Success(response.body()?.data?.toDetailModel()))
        } else {
            emit(
                ResultType.Error(
                    response.body()?.messages?.values?.joinToString { it?.firstOrNull().orEmpty() }
                        .orEmpty(),
                ),
            )
        }
    }
}
