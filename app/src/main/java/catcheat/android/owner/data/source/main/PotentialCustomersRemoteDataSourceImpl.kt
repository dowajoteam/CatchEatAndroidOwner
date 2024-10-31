package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import catcheat.android.owner.data.service.main.PotentialCustomersService
import retrofit2.Response
import javax.inject.Inject

class PotentialCustomersRemoteDataSourceImpl @Inject constructor(
    private val potentialCustomersService: PotentialCustomersService
) : PotentialCustomersRemoteDataSource {

    override suspend fun fetchPotentialCustomers(): Response<PotentialCustomersResponse> {
        return potentialCustomersService.getPotentialCustomers()
    }
}
