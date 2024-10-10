package catcheat.android.owner.data.source.intro

import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import catcheat.android.owner.data.service.intro.SignInService
import retrofit2.Response
import javax.inject.Inject

class SignInRemoteDataSourceImpl @Inject constructor(
    private val signInService: SignInService
) : SignInRemoteDataSource {
    override suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> {
        return signInService.signIn(signInRequest)
    }
}