package catcheat.android.owner.data.service.main

import catcheat.android.owner.data.model.main.StoreImageUpdateResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response
import retrofit2.http.PATCH

interface StoreImageUpdateService {
    @Multipart
    @PATCH("/api/store/main/image/")
    suspend fun updateStoreImage(
        @Part image: MultipartBody.Part
    ): Response<StoreImageUpdateResponse>
}