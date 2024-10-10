package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface VisualInfoUploadRepository {
    suspend fun uploadVisualInfo(
        guideDogAllowedDescription: RequestBody?,
        guideDogAllowedImage: MultipartBody.Part?,
        selfServiceAvailableDescription: RequestBody?,
        selfServiceAvailableImage: MultipartBody.Part?,
        centralTableSetupDescription: RequestBody?,
        centralTableSetupImage: MultipartBody.Part?
    ): Response<VisualInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}
