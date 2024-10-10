package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import catcheat.android.owner.data.service.accessibility.ServiceRequiredItemsService
import retrofit2.Response
import javax.inject.Inject

class ServiceRequiredItemsRemoteDataSourceImpl @Inject constructor(
    private val serviceRequiredItemsService: ServiceRequiredItemsService
) : ServiceRequiredItemsRemoteDataSource {

    override suspend fun fetchServiceRequiredItems(): Response<ServiceRequiredItemsResponse> {
        val token = "Token <your-token>"
        return serviceRequiredItemsService.getServiceRequiredItems(token)
    }
}