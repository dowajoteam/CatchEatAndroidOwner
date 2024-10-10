package catcheat.android.owner.data.source.intro

import catcheat.android.owner.data.model.intro.SignUpRequest
import catcheat.android.owner.data.model.intro.SignUpResponse
import catcheat.android.owner.data.service.intro.SignUpService
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class SignUpRemoteDataSourceImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpRemoteDataSource {

    override suspend fun signUp(signUpRequest: SignUpRequest, image: MultipartBody.Part?): Response<SignUpResponse> {
        val gson = Gson() // Gson 객체 생성
        val mediaType = "application/json; charset=utf-8".toMediaType()

        // SignUpRequest 객체를 JSON으로 변환
        val jsonString = gson.toJson(signUpRequest)

        val data = RequestBody.create(
            mediaType,
            jsonString // JSON 문자열로 변환된 데이터
        )

        // Retrofit 서비스 호출
        return signUpService.signUp(data, image)
    }
}
