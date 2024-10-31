package catcheat.android.owner.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import catcheat.android.owner.data.model.main.StoreInfoResponse
import catcheat.android.owner.data.repository.main.PotentialCustomersRepository
import catcheat.android.owner.data.repository.main.StoreImageUpdateRepository
import catcheat.android.owner.data.repository.main.StoreInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeInfoRepository: StoreInfoRepository,
    private val storeImageUpdateRepository: StoreImageUpdateRepository,
    private val potentialCustomersRepository: PotentialCustomersRepository
) : ViewModel() {

    private val _storeInfo = MutableLiveData<StoreInfoResponse?>()
    val storeInfo: LiveData<StoreInfoResponse?> = _storeInfo

    private val _potentialCustomers = MutableLiveData<PotentialCustomersResponse?>()
    val potentialCustomers: LiveData<PotentialCustomersResponse?> = _potentialCustomers

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
            val storeInfoResponse = storeInfoRepository.fetchStoreInfo()
            val potentialCustomersResponse = potentialCustomersRepository.getPotentialCustomers()

            if (storeInfoResponse.isSuccessful) {
                _storeInfo.value = storeInfoResponse.body()
            } else {
                _errorMessage.value = storeInfoResponse.errorBody()?.string() ?: "Store info fetch error"
            }

            if (potentialCustomersResponse.isSuccessful) {
                _potentialCustomers.value = potentialCustomersResponse.body()
            } else {
                _errorMessage.value = potentialCustomersResponse.errorBody()?.string() ?: "Potential customers fetch error"
            }

        } catch (e: Exception) {
            _errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isRequestInProgress.value = false // End request
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
}
