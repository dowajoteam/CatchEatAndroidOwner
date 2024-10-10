package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import catcheat.android.owner.data.service.ranking.PhysicalRankingService
import retrofit2.Response
import javax.inject.Inject

class PhysicalRankingRemoteDataSourceImpl @Inject constructor(
    private val rankingService: PhysicalRankingService
) : PhysicalRankingRemoteDataSource {

    override suspend fun fetchPhysicalRanking(): Response<List<PhysicalRankingResponse>> {
        return rankingService.fetchPhysicalRanking()
    }
}