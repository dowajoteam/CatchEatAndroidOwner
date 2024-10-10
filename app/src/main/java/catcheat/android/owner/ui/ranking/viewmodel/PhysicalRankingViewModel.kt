package catcheat.android.owner.ui.ranking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import catcheat.android.owner.data.repository.ranking.PhysicalRankingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhysicalRankingViewModel @Inject constructor(
    private val repository: PhysicalRankingRepository
) : ViewModel() {

    // StateFlow를 통해 UI에서 데이터 상태를 관찰할 수 있음
    private val _rankingList = MutableStateFlow<List<PhysicalRankingResponse>>(emptyList())
    val rankingList: StateFlow<List<PhysicalRankingResponse>> = _rankingList

    // 오류 상태를 저장하기 위한 변수
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchRankingData()
    }

    // 물리적 접근성 랭킹 데이터를 가져오는 함수
    private fun fetchRankingData() {
        viewModelScope.launch {
            try {
                val response = repository.fetchPhysicalRanking()

                if (response.isSuccessful) {
                    // 응답이 성공적일 경우
                    val rankingData = response.body()
                    _rankingList.value = rankingData ?: emptyList()
                } else {
                    // 응답이 실패한 경우
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                // 예외가 발생한 경우
                _errorMessage.value = e.message ?: "An unknown error occurred"
            }
        }
    }
}
