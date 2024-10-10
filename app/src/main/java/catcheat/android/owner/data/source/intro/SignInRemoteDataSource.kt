package catcheat.android.owner.data.source.intro

import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import retrofit2.Response

interface SignInRemoteDataSource {
    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse>
}