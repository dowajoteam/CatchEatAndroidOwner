package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import catcheat.android.owner.data.source.accessibility.VisualRequiredItemsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class VisualRequiredItemsRepositoryImpl @Inject constructor(
    private val remoteDataSource: VisualRequiredItemsRemoteDataSource
) : VisualRequiredItemsRepository {

    override suspend fun fetchVisualRequiredItems(): Response<VisualRequiredItemsResponse> {
        return remoteDataSource.fetchVisualRequiredItems()
    }
}