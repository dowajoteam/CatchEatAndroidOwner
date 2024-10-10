package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import catcheat.android.owner.data.service.ranking.RankingDetailsService
import retrofit2.Response
import javax.inject.Inject

class RankingDetailsRemoteDataSourceImpl @Inject constructor(
    private val rankingService: RankingDetailsService
) : RankingDetailsRemoteDataSource {

    override suspend fun getRankingDetails(storeId: Int): Response<RankingDetailsResponse> {
        return rankingService.getRankingDetails(storeId)
    }
}