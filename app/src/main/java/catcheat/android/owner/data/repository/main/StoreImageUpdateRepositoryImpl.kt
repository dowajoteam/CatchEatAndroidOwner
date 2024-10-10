package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.StoreImageUpdateResponse
import catcheat.android.owner.data.source.main.StoreImageUpdateRemoteDataSource
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class StoreImageUpdateRepositoryImpl @Inject constructor(
    private val remoteDataSource: StoreImageUpdateRemoteDataSource
) : StoreImageUpdateRepository {

    override suspend fun updateStoreImage(image: MultipartBody.Part): Response<StoreImageUpdateResponse> {
        return remoteDataSource.updateStoreImage(image)
    }
}