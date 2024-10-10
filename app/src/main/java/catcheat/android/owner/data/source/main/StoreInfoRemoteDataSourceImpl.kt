package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.StoreInfoResponse
import catcheat.android.owner.data.service.main.StoreInfoService
import retrofit2.Response
import javax.inject.Inject

class StoreInfoRemoteDataSourceImpl @Inject constructor(
    private val storeInfoService: StoreInfoService
) : StoreInfoRemoteDataSource {

    override suspend fun fetchStoreInfo(): Response<StoreInfoResponse> {
        return storeInfoService.fetchStoreInfo()
    }
}