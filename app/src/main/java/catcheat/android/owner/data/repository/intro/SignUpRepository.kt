package catcheat.android.owner.data.repository.intro

import catcheat.android.owner.data.model.intro.SignUpRequest
import catcheat.android.owner.data.model.intro.SignUpResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface SignUpRepository {
    suspend fun signUp(signUpRequest: SignUpRequest, image: MultipartBody.Part?): Response<SignUpResponse>
}