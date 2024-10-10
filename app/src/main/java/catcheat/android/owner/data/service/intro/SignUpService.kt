package catcheat.android.owner.data.service.intro

import catcheat.android.owner.data.model.intro.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignUpService {
    @Multipart
    @POST("/api/store/users/")
    suspend fun signUp(
        @Part("data") data: RequestBody, // JSON 데이터
        @Part image: MultipartBody.Part? // 이미지 파일
    ): Response<SignUpResponse> // Response 객체 사용
}


