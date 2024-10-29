package catcheat.android.owner.ui.ranking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import catcheat.android.owner.data.repository.ranking.EntireRankingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntireRankingViewModel @Inject constructor(
    private val repository: EntireRankingRepository
) : ViewModel() {

    private val _rankingList = MutableStateFlow<List<EntireRankingResponse>>(emptyList())
    val rankingList: StateFlow<List<EntireRankingResponse>> = _rankingList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchRankingData()
    }

    // 랭킹 데이터를 가져오는 함수
    private fun fetchRankingData() {
        viewModelScope.launch {
            try {
                val response = repository.fetchEntireRanking()

                if (response.isSuccessful) {
                    // 서버 응답이 성공적일 경우
                    val rankingData = response.body()
                    _rankingList.value = rankingData ?: emptyList()
                } else {
                    // 서버 응답이 실패한 경우
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                // 네트워크 오류 등 예외 처리
                _errorMessage.value = e.message ?: "An unknown error occurred"
            }
        }
    }
}
