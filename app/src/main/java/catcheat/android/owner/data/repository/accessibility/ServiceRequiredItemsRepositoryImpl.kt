package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import catcheat.android.owner.data.source.accessibility.ServiceRequiredItemsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ServiceRequiredItemsRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServiceRequiredItemsRemoteDataSource
) : ServiceRequiredItemsRepository {

    override suspend fun fetchServiceRequiredItems(): Response<ServiceRequiredItemsResponse> {
        return remoteDataSource.fetchServiceRequiredItems()
    }
}