package catcheat.android.owner.data.repository.intro

import catcheat.android.owner.data.model.intro.SignUpRequest
import catcheat.android.owner.data.model.intro.SignUpResponse
import catcheat.android.owner.data.service.intro.SignUpService
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject


class SignUpRepositoryImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpRepository {

    override suspend fun signUp(signUpRequest: SignUpRequest, image: MultipartBody.Part?): Response<SignUpResponse> {
        // JSON 데이터로 변환
        val jsonData = Gson().toJson(signUpRequest)

        // JSON 데이터를 RequestBody로 변환
        val dataRequestBody = jsonData.toRequestBody("application/json".toMediaTypeOrNull())

        // 서비스 호출 (Multipart로 데이터와 이미지를 전송)
        return signUpService.signUp(dataRequestBody, image)
    }
}
