package catcheat.android.owner.data.repository.main

import catcheat.android.owner.data.model.main.StoreImageUpdateResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface StoreImageUpdateRepository {
    suspend fun updateStoreImage(image: MultipartBody.Part): Response<StoreImageUpdateResponse>
}