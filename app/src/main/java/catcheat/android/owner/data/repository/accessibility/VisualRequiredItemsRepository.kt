package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import retrofit2.Response

interface VisualRequiredItemsRepository {
    suspend fun fetchVisualRequiredItems(): Response<VisualRequiredItemsResponse> // BaseApiResponse 대신 Response<T> 사용
}