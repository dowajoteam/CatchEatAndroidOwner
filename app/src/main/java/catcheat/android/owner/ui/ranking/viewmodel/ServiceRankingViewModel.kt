package catcheat.android.owner.ui.ranking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import catcheat.android.owner.data.repository.ranking.ServiceRankingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceRankingViewModel @Inject constructor(
    private val repository: ServiceRankingRepository
) : ViewModel() {

    // StateFlow를 통해 UI에서 데이터 상태를 관찰할 수 있음
    private val _rankingList = MutableStateFlow<List<ServiceRankingResponse>>(emptyList())
    val rankingList: StateFlow<List<ServiceRankingResponse>> = _rankingList

    // 오류 상태를 저장하기 위한 변수
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchRankingData()
    }

    // 서비스 접근성 랭킹 데이터를 가져오는 함수
    private fun fetchRankingData() {
        viewModelScope.launch {
            try {
                val response = repository.fetchServiceRanking()

                if (response.isSuccessful) {
                    // 성공적인 응답 처리
                    val rankingList = response.body()
                    _rankingList.value = rankingList ?: emptyList()
                } else {
                    // 오류 발생 시 처리
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
            }
        }
    }
}
