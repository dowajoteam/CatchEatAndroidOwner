package catcheat.android.owner.ui.accessibility.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.accessibility.ServiceInfoUploadRequest
import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import catcheat.android.owner.data.repository.accessibility.ServiceInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.ServiceRequiredItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ServiceRequiredItemsViewModel @Inject constructor(
    private val serviceRequiredItemsRepository: ServiceRequiredItemsRepository,
    private val serviceInfoUploadRepository: ServiceInfoUploadRepository
) : ViewModel() {

    private val _serviceRequiredItems = MutableStateFlow<ServiceRequiredItemsResponse?>(null)
    val serviceRequiredItems: StateFlow<ServiceRequiredItemsResponse?> = _serviceRequiredItems

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _uploadSuccess = MutableStateFlow<Boolean?>(null)
    val uploadSuccess: StateFlow<Boolean?> = _uploadSuccess

    fun fetchServiceRequiredItems() {
        viewModelScope.launch {
            try {
                val response = serviceRequiredItemsRepository.fetchServiceRequiredItems()

                if (response.isSuccessful) {
                    // 성공적인 응답 처리
                    _serviceRequiredItems.value = response.body()
                } else {
                    // 오류 발생 시 처리
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun uploadServiceInfo(
        staffCallOrderAvailableDescription: String,
        staffCallOrderAvailableImageFile: File?
    ) {
        viewModelScope.launch {
            try {
                // RequestBody 생성
                val descriptionBody = staffCallOrderAvailableDescription.toRequestBody()

                // MultipartBody.Part 생성
                val imagePart = staffCallOrderAvailableImageFile?.toMultipartBody("staff_call_order_available_image")

                // 서버로 업로드
                val response = serviceInfoUploadRepository.uploadServiceInfo(
                    descriptionBody,
                    imagePart
                )

                if (response.isSuccessful) {
                    // 성공적으로 업로드된 경우
                    _uploadSuccess.value = true
                } else {
                    // 업로드 실패 시 오류 처리
                    _errorMessage.value = response.errorBody()?.string() ?: "Image upload failed"
                    _uploadSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _uploadSuccess.value = false
            }
        }
    }

    // String을 RequestBody로 변환하는 확장 함수
    private fun String.toRequestBody(): RequestBody =
        RequestBody.create("text/plain".toMediaTypeOrNull(), this)

    // File을 MultipartBody.Part로 변환하는 확장 함수
    private fun File.toMultipartBody(partName: String): MultipartBody.Part =
        MultipartBody.Part.createFormData(partName, name, asRequestBody("image/*".toMediaTypeOrNull()))
}

