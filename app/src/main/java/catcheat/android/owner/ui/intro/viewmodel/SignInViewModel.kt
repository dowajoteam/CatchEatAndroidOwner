package catcheat.android.owner.ui.intro.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import catcheat.android.owner.data.network.TokenProvider
import catcheat.android.owner.data.repository.intro.SignInRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository,
    private val tokenProvider: TokenProvider
) : ViewModel() {

    private val _signInResult = MutableStateFlow<SignInResponse?>(null)
    val signInResult: StateFlow<SignInResponse?> = _signInResult

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // 아이디 : 영문 소문자, 숫자 포함 (5~10자)
    private val idPattern = Pattern.compile("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{5,10}$")
    // 비밀번호 : 영문 소문자, 숫자, 특수문자 포함 (8~16자)
    private val passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+=-])[a-z0-9!@#\$%^&*()_+=-]{8,16}$")

    // 네트워크 요청 반복 방지를 위한 상태 변수
    private val isRequestCompleted = mutableStateOf(false)

    fun signIn(id: String, password: String) {
        if (!isIdValid(id)) {
            return
        }

        if (!isPasswordValid(password)) {
            return
        }

        // 요청이 이미 완료된 경우 추가 요청을 방지
        if (isRequestCompleted.value) {
            Log.d("SignInViewModel", "Request already completed, ignoring further requests.")
            return
        }

        val signInRequest = SignInRequest(id = id, password = password)
        viewModelScope.launch {
            try {
                val response = repository.signIn(signInRequest)

                if (response.isSuccessful) {
                    response.body()?.let { signInResponse ->
                        _signInResult.value = signInResponse
                        signInResponse.token?.let { token ->
                            tokenProvider.saveToken(token)
                            Log.d("SignInViewModel", "Login successful: $token")
                            val savedToken = tokenProvider.getToken()
                            Log.d("SignInViewModel", "Stored Token: $savedToken")
                            isRequestCompleted.value = true  // 요청 완료 후 상태 변경
                        }
                    }
                } else {
                    response.errorBody()?.let { errorBody ->
                        val errorResponse = Gson().fromJson(errorBody.string(), SignInResponse::class.java)
                        _errorMessage.value = errorResponse.error
                        Log.e("SignInViewModel", "Login failed: ${_errorMessage.value}")
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e("SignInViewModel", "Error occurred: ${e.localizedMessage}")
            }
        }
    }

    // 아이디 유효성 검사
    fun isIdValid(id: String): Boolean {
        return idPattern.matcher(id).matches()
    }

    // 비밀번호 유효성 검사
    fun isPasswordValid(password: String): Boolean {
        return passwordPattern.matcher(password).matches()
    }
}

