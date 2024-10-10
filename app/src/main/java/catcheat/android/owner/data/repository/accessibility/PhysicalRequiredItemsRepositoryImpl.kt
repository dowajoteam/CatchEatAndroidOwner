package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import catcheat.android.owner.data.source.accessibility.PhysicalRequiredItemsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PhysicalRequiredItemsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PhysicalRequiredItemsRemoteDataSource
) : PhysicalRequiredItemsRepository {

    override suspend fun fetchPhysicalRequiredItems(): Response<PhysicalRequiredItemsResponse> {
        return remoteDataSource.fetchPhysicalRequiredItems()
    }
}