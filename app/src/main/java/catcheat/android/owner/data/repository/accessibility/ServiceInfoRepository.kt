package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import retrofit2.Response

interface ServiceInfoRepository {
    suspend fun fetchServiceInfo(): Response<ServiceInfoResponse>
}