package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.StoreInfoResponse
import retrofit2.Response

interface StoreInfoRemoteDataSource {
    suspend fun fetchStoreInfo(): Response<StoreInfoResponse>
}