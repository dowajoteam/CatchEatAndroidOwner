package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import retrofit2.Response

interface VisualRequiredItemsRemoteDataSource {
    suspend fun fetchVisualRequiredItems(): Response<VisualRequiredItemsResponse>
}