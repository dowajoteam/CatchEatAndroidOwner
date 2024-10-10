package catcheat.android.owner.data.repository.intro

import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import retrofit2.Response

interface SignInRepository {
    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse>
}