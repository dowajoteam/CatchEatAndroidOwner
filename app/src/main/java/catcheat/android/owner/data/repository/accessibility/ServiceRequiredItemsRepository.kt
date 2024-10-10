package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import retrofit2.Response

interface ServiceRequiredItemsRepository {
    suspend fun fetchServiceRequiredItems(): Response<ServiceRequiredItemsResponse>
}