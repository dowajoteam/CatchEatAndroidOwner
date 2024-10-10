package catcheat.android.owner.data.service.main

import catcheat.android.owner.data.model.main.StoreInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface StoreInfoService {
    @GET("/api/store/main/")
    suspend fun fetchStoreInfo(): Response<StoreInfoResponse>
}