package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import catcheat.android.owner.data.source.accessibility.ServiceInfoUploadRemoteDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ServiceInfoUploadRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServiceInfoUploadRemoteDataSource
) : ServiceInfoUploadRepository {

    override suspend fun uploadServiceInfo(
        staffCallOrderAvailableDescription: RequestBody?,
        staffCallOrderAvailableImage: MultipartBody.Part?
    ): Response<ServiceInfoResponse> {
        return remoteDataSource.uploadServiceInfo(
            staffCallOrderAvailableDescription, staffCallOrderAvailableImage
        )
    }
}