package catcheat.android.owner.data.source.main

import catcheat.android.owner.data.model.main.StoreImageUpdateResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface StoreImageUpdateRemoteDataSource {
    suspend fun updateStoreImage(image: MultipartBody.Part): Response<StoreImageUpdateResponse>
}