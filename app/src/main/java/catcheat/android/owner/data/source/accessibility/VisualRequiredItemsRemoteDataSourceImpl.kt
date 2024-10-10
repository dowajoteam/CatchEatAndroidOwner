package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import catcheat.android.owner.data.service.accessibility.VisualRequiredItemsService
import retrofit2.Response
import javax.inject.Inject

class VisualRequiredItemsRemoteDataSourceImpl @Inject constructor(
    private val visualRequiredItemsService: VisualRequiredItemsService
) : VisualRequiredItemsRemoteDataSource {

    override suspend fun fetchVisualRequiredItems(): Response<VisualRequiredItemsResponse> {
        val token = "Token <your-token>"
        return visualRequiredItemsService.getVisualRequiredItems(token)
    }
}