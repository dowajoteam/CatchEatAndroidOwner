package catcheat.android.owner.ui.ranking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import catcheat.android.owner.data.repository.ranking.RankingDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingDetailsViewModel @Inject constructor(
    private val repository: RankingDetailsRepository
) : ViewModel() {

    private val _rankingDetails = MutableStateFlow<RankingDetailsResponse?>(null)
    val rankingDetails: StateFlow<RankingDetailsResponse?> = _rankingDetails

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchRankingDetails(storeId: Int) {
        viewModelScope.launch {
            try {
                Log.d("RankingDetailsViewModel", "Fetching details for storeId: $storeId")
                val response = repository.getRankingDetails(storeId)

                if (response.isSuccessful) {
                    val details = response.body()
                    _rankingDetails.value = details
                    Log.d("RankingDetailsViewModel", "Success: $details")
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                    Log.e("RankingDetailsViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
                Log.e("RankingDetailsViewModel", "Exception: ${e.message}")
            }
        }
    }
}
