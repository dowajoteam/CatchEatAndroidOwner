package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface VisualInfoUploadService {

    @Multipart
    @PATCH("/api/store/visual/accessibility/")
    suspend fun uploadVisualInfo(
        @Header("Authorization") token: String,
        @Part("guide_dog_allowed_description") guideDogAllowedDescription: RequestBody?,
        @Part guideDogAllowedImage: MultipartBody.Part?,
        @Part("self_service_available_description") selfServiceAvailableDescription: RequestBody?,
        @Part selfServiceAvailableImage: MultipartBody.Part?,
        @Part("central_table_setup_description") centralTableSetupDescription: RequestBody?,
        @Part centralTableSetupImage: MultipartBody.Part?
    ): Response<VisualInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}