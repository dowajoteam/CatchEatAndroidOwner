package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface ServiceInfoUploadRemoteDataSource {
    suspend fun uploadServiceInfo(
        staffCallOrderAvailableDescription: RequestBody?,
        staffCallOrderAvailableImage: MultipartBody.Part?
    ): Response<ServiceInfoResponse>
}