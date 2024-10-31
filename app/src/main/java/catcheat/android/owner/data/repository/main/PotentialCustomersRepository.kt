package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import retrofit2.Response

interface PotentialCustomersRepository {
    suspend fun getPotentialCustomers(): Response<PotentialCustomersResponse>
}
