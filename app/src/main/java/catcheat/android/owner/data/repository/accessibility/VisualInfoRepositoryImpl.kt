package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import catcheat.android.owner.data.source.accessibility.VisualInfoRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class VisualInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: VisualInfoRemoteDataSource
) : VisualInfoRepository {

    override suspend fun fetchVisualInfo(): Response<VisualInfoResponse> {
        return remoteDataSource.fetchVisualInfo()
    }
}