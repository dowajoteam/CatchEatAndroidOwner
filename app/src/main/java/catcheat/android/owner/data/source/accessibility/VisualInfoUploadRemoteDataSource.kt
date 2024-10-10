package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface VisualInfoUploadRemoteDataSource {
    suspend fun uploadVisualInfo(
        guideDogAllowedDescription: RequestBody?,
        guideDogAllowedImage: MultipartBody.Part?,
        selfServiceAvailableDescription: RequestBody?,
        selfServiceAvailableImage: MultipartBody.Part?,
        centralTableSetupDescription: RequestBody?,
        centralTableSetupImage: MultipartBody.Part?
    ): Response<VisualInfoResponse>
}