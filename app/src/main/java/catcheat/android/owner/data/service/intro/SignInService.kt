package catcheat.android.owner.data.service.intro

import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/api/store/users/login/")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>
}