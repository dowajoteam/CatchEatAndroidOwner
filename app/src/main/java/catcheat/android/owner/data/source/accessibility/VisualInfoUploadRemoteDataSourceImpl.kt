package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import catcheat.android.owner.data.service.accessibility.VisualInfoUploadService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class VisualInfoUploadRemoteDataSourceImpl @Inject constructor(
    private val visualInfoUploadService: VisualInfoUploadService
) : VisualInfoUploadRemoteDataSource {

    override suspend fun uploadVisualInfo(
        guideDogAllowedDescription: RequestBody?,
        guideDogAllowedImage: MultipartBody.Part?,
        selfServiceAvailableDescription: RequestBody?,
        selfServiceAvailableImage: MultipartBody.Part?,
        centralTableSetupDescription: RequestBody?,
        centralTableSetupImage: MultipartBody.Part?
    ): Response<VisualInfoResponse> {
        val token = "Token <your-token>"
        return visualInfoUploadService.uploadVisualInfo(
            token,
            guideDogAllowedDescription, guideDogAllowedImage,
            selfServiceAvailableDescription, selfServiceAvailableImage,
            centralTableSetupDescription, centralTableSetupImage
        )
    }
}