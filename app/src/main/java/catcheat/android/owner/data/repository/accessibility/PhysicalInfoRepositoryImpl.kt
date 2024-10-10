package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import catcheat.android.owner.data.source.accessibility.PhysicalInfoRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PhysicalInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: PhysicalInfoRemoteDataSource
) : PhysicalInfoRepository {

    override suspend fun fetchPhysicalInfo(): Response<PhysicalInfoResponse> {
        return remoteDataSource.fetchPhysicalInfo()
    }
}