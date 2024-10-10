package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import catcheat.android.owner.data.service.accessibility.PhysicalRequiredItemsService
import retrofit2.Response
import javax.inject.Inject

class PhysicalRequiredItemsRemoteDataSourceImpl @Inject constructor(
    private val physicalRequiredItemsService: PhysicalRequiredItemsService
) : PhysicalRequiredItemsRemoteDataSource {

    override suspend fun fetchPhysicalRequiredItems(): Response<PhysicalRequiredItemsResponse> {
        val token = "Token <your-token>"
        return physicalRequiredItemsService.getPhysicalRequiredItems(token)
    }
}