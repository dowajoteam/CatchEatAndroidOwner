package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import catcheat.android.owner.data.source.accessibility.ServiceInfoRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ServiceInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServiceInfoRemoteDataSource
) : ServiceInfoRepository {

    override suspend fun fetchServiceInfo(): Response<ServiceInfoResponse> {
        return remoteDataSource.fetchServiceInfo()
    }
}