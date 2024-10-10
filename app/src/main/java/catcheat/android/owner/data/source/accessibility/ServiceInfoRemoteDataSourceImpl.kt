package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import catcheat.android.owner.data.service.accessibility.ServiceInfoService
import retrofit2.Response
import javax.inject.Inject

class ServiceInfoRemoteDataSourceImpl @Inject constructor(
    private val serviceInfoService: ServiceInfoService
) : ServiceInfoRemoteDataSource {

    override suspend fun fetchServiceInfo(): Response<ServiceInfoResponse> {
        val token = "Token <your-token>"
        return serviceInfoService.getServiceInfo(token)
    }
}