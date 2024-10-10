package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.StoreInfoResponse
import retrofit2.Response

interface StoreInfoRepository {
    suspend fun fetchStoreInfo(): Response<StoreInfoResponse>
}
