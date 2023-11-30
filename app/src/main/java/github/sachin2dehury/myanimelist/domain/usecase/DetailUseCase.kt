package github.sachin2dehury.myanimelist.domain.usecase

import github.sachin2dehury.myanimelist.domain.ResultType
import github.sachin2dehury.myanimelist.domain.model.DetailModel
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend operator fun invoke(id: Int): Flow<ResultType<DetailModel?>>
}
