package catcheat.android.owner.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.main.StoreInfoResponse
import catcheat.android.owner.data.repository.main.StoreImageUpdateRepository
import catcheat.android.owner.data.repository.main.StoreInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeInfoRepository: StoreInfoRepository,
    private val storeImageUpdateRepository: StoreImageUpdateRepository
) : ViewModel() {

    private val _storeInfo = MutableStateFlow<StoreInfoResponse?>(null)
    val storeInfo: StateFlow<StoreInfoResponse?> = _storeInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchStoreInfo() {
        viewModelScope.launch {
            try {
                val response = storeInfoRepository.fetchStoreInfo()

                if (response.isSuccessful) {
                    _storeInfo.value = response.body()
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
            }
        }
    }

    fun updateStoreImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = storeImageUpdateRepository.updateStoreImage(image)

                if (response.isSuccessful) {
                    // 이미지 업로드가 성공하면, 캐시된 이미지를 강제로 무시하기 위해 새로운 이미지 URL에 쿼리 파라미터 추가
                    val updatedImageUrl = response.body()?.imageUrl + "?time=" + System.currentTimeMillis()

                    // 새로운 값을 적용하여 UI가 갱신되도록 처리
                    _storeInfo.value = _storeInfo.value?.copy(image = updatedImageUrl)
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Image update failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
            }
        }
    }
}

