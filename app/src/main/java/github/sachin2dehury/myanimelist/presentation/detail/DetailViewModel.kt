package github.sachin2dehury.myanimelist.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.myanimelist.domain.ResultType
import github.sachin2dehury.myanimelist.domain.usecase.DetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: DetailUseCase) : ViewModel() {

    private val _data = MutableStateFlow(DetailUiModel())
    val data = _data.asStateFlow()

    fun getDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.invoke(id).onEach { result ->
            when (result) {
                is ResultType.Loading -> _data.update {
                    it.copy(isLoading = true)
                }

                is ResultType.Error -> _data.update {
                    it.copy(
                        isLoading = false,
                        error = result.message,
                    )
                }

                is ResultType.Success -> _data.update {
                    it.copy(
                        isLoading = false,
                        data = result.data,
                        error = null,
                    )
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }
}
