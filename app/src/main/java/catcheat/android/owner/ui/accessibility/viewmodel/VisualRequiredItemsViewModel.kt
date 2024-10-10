package catcheat.android.owner.ui.accessibility.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.accessibility.VisualInfoUploadRequest
import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import catcheat.android.owner.data.repository.accessibility.VisualInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.VisualRequiredItemsRepository
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
class VisualRequiredItemsViewModel @Inject constructor(
    private val visualRequiredItemsRepository: VisualRequiredItemsRepository,
    private val visualInfoUploadRepository: VisualInfoUploadRepository
) : ViewModel() {

    private val _visualRequiredItems = MutableStateFlow<VisualRequiredItemsResponse?>(null)
    val visualRequiredItems: StateFlow<VisualRequiredItemsResponse?> = _visualRequiredItems

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _uploadSuccess = MutableStateFlow<Boolean?>(null)
    val uploadSuccess: StateFlow<Boolean?> = _uploadSuccess

    // 서비스 필수 항목 불러오는 함수
    fun fetchVisualRequiredItems() {
        viewModelScope.launch {
            try {
                val response = visualRequiredItemsRepository.fetchVisualRequiredItems()
                if (response.isSuccessful) {
                    _visualRequiredItems.value = response.body()
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error occurred"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    // 서비스 정보 업로드 함수
    fun uploadVisualInfo(
        guideDogAllowedDescription: String?,
        guideDogAllowedImageFile: File?,
        selfServiceAvailableDescription: String?,
        selfServiceAvailableImageFile: File?,
        centralTableSetupDescription: String?,
        centralTableSetupImageFile: File?
    ) {
        viewModelScope.launch {
            try {
                val guideDogAllowedDescBody = guideDogAllowedDescription?.toRequestBody()
                val selfServiceAvailableDescBody = selfServiceAvailableDescription?.toRequestBody()
                val centralTableSetupDescBody = centralTableSetupDescription?.toRequestBody()

                val guideDogAllowedImagePart = guideDogAllowedImageFile?.toMultipartBody("guide_dog_allowed_image")
                val selfServiceAvailableImagePart = selfServiceAvailableImageFile?.toMultipartBody("self_service_available_image")
                val centralTableSetupImagePart = centralTableSetupImageFile?.toMultipartBody("central_table_setup_image")

                val response = visualInfoUploadRepository.uploadVisualInfo(
                    guideDogAllowedDescBody,
                    guideDogAllowedImagePart,
                    selfServiceAvailableDescBody,
                    selfServiceAvailableImagePart,
                    centralTableSetupDescBody,
                    centralTableSetupImagePart
                )

                if (response.isSuccessful) {
                    _uploadSuccess.value = true
                } else {
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