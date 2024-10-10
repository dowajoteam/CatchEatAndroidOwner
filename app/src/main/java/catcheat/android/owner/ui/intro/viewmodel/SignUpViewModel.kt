package catcheat.android.owner.ui.intro.viewmodel

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.intro.SignUpRequest
import catcheat.android.owner.data.model.intro.SignUpResponse
import catcheat.android.owner.data.repository.intro.SignUpRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _signUpResponse = MutableStateFlow<SignUpResponse?>(null)
    val signUpResponse: StateFlow<SignUpResponse?> = _signUpResponse

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private var imageUri: Uri? = null

    // 이미지 URI 설정 함수
    fun setImageUri(uri: Uri?) {
        imageUri = uri
    }

    fun signUp(
        id: String,
        password: String,
        storeName: String,
        businessType: String,
        phoneNumber: String,
        address: String
    ) {
        viewModelScope.launch {
            try {
                // 이미지가 선택되지 않은 경우 에러 처리
                if (imageUri == null) {
                    _errorMessage.value = "이미지를 선택해 주세요."
                    return@launch
                }

                // SignUpRequest 객체 생성
                val signUpData = SignUpRequest(
                    id = id,
                    password = password,
                    storeName = storeName,
                    businessType = businessType,
                    phoneNumber = phoneNumber,
                    address = address
                )

                // 이미지 파일을 MultipartBody.Part로 변환
                val imagePart = convertUriToImagePart(imageUri, getApplication<Application>().applicationContext)

                // 서버에 데이터 전송 (Response 객체를 사용)
                val response = signUpRepository.signUp(signUpData, imagePart)

                if (response.isSuccessful) {
                    // 서버에서 받은 응답이 성공적인 경우
                    _signUpResponse.value = response.body()
                    Log.d("SignUpViewModel", "SignUp successful: ${response.body()}")
                } else {
                    // 실패한 경우, 오류 메시지를 파싱
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = errorBody ?: "Unknown error occurred"
                    Log.e("SignUpViewModel", "SignUp failed: $errorBody")
                }
            } catch (e: Exception) {
                // 예외가 발생한 경우 (네트워크 오류나 데이터 처리 중 문제가 발생한 경우)
                _errorMessage.value = e.message
                Log.e("SignUpViewModel", "Error occurred: ${e.localizedMessage}")
            }
        }
    }

    // 이미지 URI를 MultipartBody.Part로 변환하는 함수
    fun convertUriToImagePart(uri: Uri?, context: Context): MultipartBody.Part? {
        return uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val file = File.createTempFile("upload", ".jpg", context.cacheDir)
            inputStream?.use { input -> file.outputStream().use { output -> input.copyTo(output) } }

            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestFile)
        }
    }
}

