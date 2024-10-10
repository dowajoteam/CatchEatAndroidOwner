package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.StoreImageUpdateResponse
import catcheat.android.owner.data.service.main.StoreImageUpdateService
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class StoreImageUpdateRemoteDataSourceImpl @Inject constructor(
    private val storeImageUpdateService: StoreImageUpdateService
) : StoreImageUpdateRemoteDataSource {

    override suspend fun updateStoreImage(image: MultipartBody.Part): Response<StoreImageUpdateResponse> {
        return storeImageUpdateService.updateStoreImage(image)
    }
}