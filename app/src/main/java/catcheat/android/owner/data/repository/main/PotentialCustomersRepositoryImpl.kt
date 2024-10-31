package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import catcheat.android.owner.data.source.main.PotentialCustomersRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PotentialCustomersRepositoryImpl @Inject constructor(
    private val remoteDataSource: PotentialCustomersRemoteDataSource
) : PotentialCustomersRepository {

    override suspend fun getPotentialCustomers(): Response<PotentialCustomersResponse> {
        return remoteDataSource.fetchPotentialCustomers()
    }
}
