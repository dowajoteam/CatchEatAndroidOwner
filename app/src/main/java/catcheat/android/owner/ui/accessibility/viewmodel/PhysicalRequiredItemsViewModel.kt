package catcheat.android.owner.ui.accessibility.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import catcheat.android.owner.data.repository.accessibility.PhysicalInfoRepository
import catcheat.android.owner.data.repository.accessibility.PhysicalInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.PhysicalRequiredItemsRepository
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
class PhysicalRequiredItemsViewModel @Inject constructor(
    private val physicalRequiredItemsRepository: PhysicalRequiredItemsRepository,
    private val physicalInfoRepository: PhysicalInfoRepository,
    private val physicalInfoUploadRepository: PhysicalInfoUploadRepository
) : ViewModel() {

    private val _physicalRequiredItems = MutableStateFlow<PhysicalRequiredItemsResponse?>(null)
    val physicalRequiredItems: StateFlow<PhysicalRequiredItemsResponse?> = _physicalRequiredItems

    private val _physicalInfo = MutableStateFlow<PhysicalInfoResponse?>(null)
    val physicalInfo: StateFlow<PhysicalInfoResponse?> = _physicalInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _uploadSuccess = MutableStateFlow<Boolean?>(null)
    val uploadSuccess: StateFlow<Boolean?> = _uploadSuccess

    // 두 가지 정보를 함께 불러오는 함수
    fun fetchPhysicalInfoAndRequiredItems() {
        viewModelScope.launch {
            try {
                val requiredItemsResponse = physicalRequiredItemsRepository.fetchPhysicalRequiredItems()
                val infoResponse = physicalInfoRepository.fetchPhysicalInfo()

                if (requiredItemsResponse.isSuccessful && infoResponse.isSuccessful) {
                    _physicalRequiredItems.value = requiredItemsResponse.body()
                    _physicalInfo.value = infoResponse.body()
                } else {
                    _errorMessage.value = "데이터를 불러오지 못했습니다."
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "알 수 없는 오류가 발생했습니다."
            }
        }
    }

    fun uploadPhysicalInfo(
        doorWidthOver90cmDescription: String?,
        doorWidthOver90cmImageFile: File?,
        rampOrNoThresholdDescription: String?,
        rampOrNoThresholdImageFile: File?,
        automaticDoorOrHandleDescription: String?,
        automaticDoorOrHandleImageFile: File?,
        wheelchairSpaceDescription: String?,
        wheelchairSpaceImageFile: File?,
        wheelchairParkingSpaceDescription: String?,
        wheelchairParkingSpaceImageFile: File?,
        disabledToiletDescription: String?,
        disabledToiletImageFile: File?
    ) {
        viewModelScope.launch {
            try {
                // RequestBody 생성
                val doorWidthOver90cmDescBody = doorWidthOver90cmDescription?.toRequestBody()
                val rampOrNoThresholdDescBody = rampOrNoThresholdDescription?.toRequestBody()
                val automaticDoorOrHandleDescBody = automaticDoorOrHandleDescription?.toRequestBody()
                val wheelchairSpaceDescBody = wheelchairSpaceDescription?.toRequestBody()
                val wheelchairParkingSpaceDescBody = wheelchairParkingSpaceDescription?.toRequestBody()
                val disabledToiletDescBody = disabledToiletDescription?.toRequestBody()

                // MultipartBody.Part 생성
                val doorWidthOver90cmImagePart = doorWidthOver90cmImageFile?.toMultipartBody("door_width_over_90cm_image")
                val rampOrNoThresholdImagePart = rampOrNoThresholdImageFile?.toMultipartBody("ramp_or_no_threshold_image")
                val automaticDoorOrHandleImagePart = automaticDoorOrHandleImageFile?.toMultipartBody("automatic_door_or_handle_image")
                val wheelchairSpaceImagePart = wheelchairSpaceImageFile?.toMultipartBody("wheelchair_space_image")
                val wheelchairParkingSpaceImagePart = wheelchairParkingSpaceImageFile?.toMultipartBody("wheelchair_parking_space_image")
                val disabledToiletImagePart = disabledToiletImageFile?.toMultipartBody("disabled_toilet_image")

                // null이 아닌 필드만 전송
                val response = physicalInfoUploadRepository.uploadPhysicalInfo(
                    doorWidthOver90cmDescBody,
                    doorWidthOver90cmImagePart,
                    rampOrNoThresholdDescBody,
                    rampOrNoThresholdImagePart,
                    automaticDoorOrHandleDescBody,
                    automaticDoorOrHandleImagePart,
                    wheelchairSpaceDescBody,
                    wheelchairSpaceImagePart,
                    wheelchairParkingSpaceDescBody,
                    wheelchairParkingSpaceImagePart,
                    disabledToiletDescBody,
                    disabledToiletImagePart
                )

                if (response.isSuccessful) {
                    _uploadSuccess.value = true
                    // 응답 데이터로 UI를 갱신
                    _physicalInfo.value = response.body()
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Image upload failed"
                    _uploadSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
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
