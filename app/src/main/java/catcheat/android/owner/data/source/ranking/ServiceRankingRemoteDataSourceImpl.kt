package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import catcheat.android.owner.data.service.ranking.ServiceRankingService
import retrofit2.Response
import javax.inject.Inject

class ServiceRankingRemoteDataSourceImpl @Inject constructor(
    private val rankingService: ServiceRankingService
) : ServiceRankingRemoteDataSource {

    override suspend fun fetchServiceRanking(): Response<List<ServiceRankingResponse>> {
        return rankingService.fetchServiceRanking()
    }
}