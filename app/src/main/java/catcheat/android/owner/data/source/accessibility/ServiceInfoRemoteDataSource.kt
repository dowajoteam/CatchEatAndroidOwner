package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import retrofit2.Response

interface ServiceInfoRemoteDataSource {
    suspend fun fetchServiceInfo(): Response<ServiceInfoResponse>
}