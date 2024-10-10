package catcheat.android.owner.ui.intro.viewmodel

import android.util.Log
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

    fun signIn(id: String, password: String) {
        val signInRequest = SignInRequest(id = id, password = password)
        viewModelScope.launch {
            try {
                val response = repository.signIn(signInRequest)

                if (response.isSuccessful) {
                    response.body()?.let { signInResponse ->
                        _signInResult.value = signInResponse
                        signInResponse.token?.let { token ->
                            // 토큰을 TokenProvider에 저장
                            tokenProvider.saveToken(token)
                            Log.d("SignInViewModel", "Login successful: $token")
                            val savedToken = tokenProvider.getToken()
                            Log.d("SignInViewModel", "Stored Token: $savedToken")
                        }
                    }
                } else {
                    response.errorBody()?.let { errorBody ->
                        val errorResponse = Gson().fromJson(errorBody.string(), SignInResponse::class.java)
                        _errorMessage.value = errorResponse.error ?: "Unknown error occurred"
                        Log.e("SignInViewModel", "Login failed: ${_errorMessage.value}")
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
                Log.e("SignInViewModel", "Error occurred: ${e.localizedMessage}")
            }
        }
    }
}
