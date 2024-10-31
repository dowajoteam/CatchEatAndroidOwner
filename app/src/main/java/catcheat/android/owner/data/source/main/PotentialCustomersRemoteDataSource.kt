package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import retrofit2.Response

interface PotentialCustomersRemoteDataSource {
    suspend fun fetchPotentialCustomers(): Response<PotentialCustomersResponse>
}
