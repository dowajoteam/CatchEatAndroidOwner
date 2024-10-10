package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import retrofit2.Response

interface PhysicalRequiredItemsRepository {
    suspend fun fetchPhysicalRequiredItems(): Response<PhysicalRequiredItemsResponse>
}
