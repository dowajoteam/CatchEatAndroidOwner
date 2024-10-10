package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import catcheat.android.owner.data.source.ranking.ServiceRankingRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ServiceRankingRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServiceRankingRemoteDataSource
) : ServiceRankingRepository {

    override suspend fun fetchServiceRanking(): Response<List<ServiceRankingResponse>> {
        return remoteDataSource.fetchServiceRanking()
    }
}