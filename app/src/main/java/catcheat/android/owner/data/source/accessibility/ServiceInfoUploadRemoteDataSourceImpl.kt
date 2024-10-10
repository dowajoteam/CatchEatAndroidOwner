package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import catcheat.android.owner.data.service.accessibility.ServiceInfoUploadService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ServiceInfoUploadRemoteDataSourceImpl @Inject constructor(
    private val serviceInfoUploadService: ServiceInfoUploadService
) : ServiceInfoUploadRemoteDataSource {

    override suspend fun uploadServiceInfo(
        staffCallOrderAvailableDescription: RequestBody?,
        staffCallOrderAvailableImage: MultipartBody.Part?
    ): Response<ServiceInfoResponse> {
        val token = "Token <your-token>"
        return serviceInfoUploadService.uploadServiceInfo(
            token, staffCallOrderAvailableDescription, staffCallOrderAvailableImage
        )
    }
}