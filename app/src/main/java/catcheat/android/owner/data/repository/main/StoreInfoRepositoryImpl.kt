package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.StoreInfoResponse
import catcheat.android.owner.data.source.main.StoreInfoRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class StoreInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: StoreInfoRemoteDataSource
) : StoreInfoRepository {

    override suspend fun fetchStoreInfo(): Response<StoreInfoResponse> {
        return remoteDataSource.fetchStoreInfo()
    }
}