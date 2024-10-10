package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import retrofit2.Response

interface PhysicalRequiredItemsRemoteDataSource {
    suspend fun fetchPhysicalRequiredItems(): Response<PhysicalRequiredItemsResponse>
}