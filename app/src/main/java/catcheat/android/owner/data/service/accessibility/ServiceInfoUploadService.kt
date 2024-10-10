package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface ServiceInfoUploadService {

    @Multipart
    @PATCH("/api/store/service/accessibility/")
    suspend fun uploadServiceInfo(
        @Header("Authorization") token: String,
        @Part("staff_call_order_available_description") staffCallOrderAvailableDescription: RequestBody?,
        @Part staffCallOrderAvailableImage: MultipartBody.Part?
    ): Response<ServiceInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}