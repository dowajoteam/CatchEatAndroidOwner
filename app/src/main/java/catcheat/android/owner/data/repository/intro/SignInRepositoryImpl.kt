package catcheat.android.owner.data.repository.intro

import catcheat.android.owner.data.model.intro.SignInRequest
import catcheat.android.owner.data.model.intro.SignInResponse
import catcheat.android.owner.data.source.intro.SignInRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val remoteDataSource: SignInRemoteDataSource
) : SignInRepository {
    override suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> {
        return remoteDataSource.signIn(signInRequest)
    }
}