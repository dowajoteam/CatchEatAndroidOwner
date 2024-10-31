package catcheat.android.owner.data.service.main

import catcheat.android.owner.data.model.main.PotentialCustomersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PotentialCustomersService {
    @GET("/api/store/main/potential/")
    suspend fun getPotentialCustomers(): Response<PotentialCustomersResponse>
}
