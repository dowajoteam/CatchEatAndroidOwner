package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import retrofit2.Response

interface ServiceRequiredItemsRemoteDataSource {
    suspend fun fetchServiceRequiredItems(): Response<ServiceRequiredItemsResponse>
}