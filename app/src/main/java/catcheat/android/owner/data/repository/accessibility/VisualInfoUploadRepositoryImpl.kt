package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import catcheat.android.owner.data.source.accessibility.VisualInfoUploadRemoteDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class VisualInfoUploadRepositoryImpl @Inject constructor(
    private val remoteDataSource: VisualInfoUploadRemoteDataSource
) : VisualInfoUploadRepository {

    override suspend fun uploadVisualInfo(
        guideDogAllowedDescription: RequestBody?,
        guideDogAllowedImage: MultipartBody.Part?,
        selfServiceAvailableDescription: RequestBody?,
        selfServiceAvailableImage: MultipartBody.Part?,
        centralTableSetupDescription: RequestBody?,
        centralTableSetupImage: MultipartBody.Part?
    ): Response<VisualInfoResponse> {
        return remoteDataSource.uploadVisualInfo(
            guideDogAllowedDescription, guideDogAllowedImage,
            selfServiceAvailableDescription, selfServiceAvailableImage,
            centralTableSetupDescription, centralTableSetupImage
        )
    }
}