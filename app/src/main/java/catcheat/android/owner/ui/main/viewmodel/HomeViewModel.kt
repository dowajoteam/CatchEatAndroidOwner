package catcheat.android.owner.ui.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.main.StoreInfoResponse
import catcheat.android.owner.data.repository.main.StoreImageUpdateRepository
import catcheat.android.owner.data.repository.main.StoreInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeInfoRepository: StoreInfoRepository,
    private val storeImageUpdateRepository: StoreImageUpdateRepository
) : ViewModel() {

    private val _storeInfo = MutableLiveData<StoreInfoResponse?>()
    val storeInfo: LiveData<StoreInfoResponse?> = _storeInfo

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isRequestInProgress = MutableLiveData(false) // 네트워크 요청 상태 플래그
    val isRequestInProgress: LiveData<Boolean> = _isRequestInProgress

    private val _isImageUpdateInProgress = MutableLiveData(false) // 이미지 업데이트 상태
    val isImageUpdateInProgress: LiveData<Boolean> = _isImageUpdateInProgress

    fun refresh() {
        if (_isRequestInProgress.value == true) return // 요청이 진행 중일 때 새 요청 차단

        viewModelScope.launch {
            fetchStoreInfo()
        }
    }
    suspend fun fetchStoreInfo() {
        if (_isRequestInProgress.value == true) return // 요청이 진행 중일 때 새 요청 차단

        _isRequestInProgress.value = true // 요청 시작
        try {
            val response = storeInfoRepository.fetchStoreInfo()
            if (response.isSuccessful) {
                _storeInfo.value = response.body()
            } else {
                _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
            }
        } catch (e: Exception) {
            _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isRequestInProgress.value = false // 요청 종료
        }
    }

    fun refreshImage(image: MultipartBody.Part) {
        if (_isImageUpdateInProgress.value == true) return // 요청이 진행 중일 때 새 요청 차단

        viewModelScope.launch {
            updateStoreImage(image)
        }
    }
    suspend fun updateStoreImage(image: MultipartBody.Part) {
        if (_isImageUpdateInProgress.value == true) return // 요청이 진행 중일 때 새 요청 차단

        _isImageUpdateInProgress.value = true // 요청 시작
        try {
            val response = storeImageUpdateRepository.updateStoreImage(image)
            if (response.isSuccessful) {
                val updatedImageUrl = response.body()?.imageUrl + "?time=" + System.currentTimeMillis()
                _storeInfo.value = _storeInfo.value?.copy(image = updatedImageUrl)
            } else {
                _errorMessage.value = response.errorBody()?.string() ?: "Image update failed"
            }
        } catch (e: Exception) {
            _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isImageUpdateInProgress.value = false // 요청 종료
        }
    }

//    fun fetchStoreInfo() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = storeInfoRepository.fetchStoreInfo()
//                if (response.isSuccessful) {
//                    _storeInfo.value = response.body()
//                } else {
//                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
//            }
//        }
//    }

//    fun updateStoreImage(image: MultipartBody.Part) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = storeImageUpdateRepository.updateStoreImage(image)
//                if (response.isSuccessful) {
//                    val updatedImageUrl = response.body()?.imageUrl + "?time=" + System.currentTimeMillis()
//                    _storeInfo.value = _storeInfo.value?.copy(image = updatedImageUrl)
//                } else {
//                    _errorMessage.value = response.errorBody()?.string() ?: "Image update failed"
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
//            }
//        }
//    }
}
