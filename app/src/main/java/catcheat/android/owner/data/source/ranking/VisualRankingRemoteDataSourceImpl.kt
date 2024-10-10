package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.VisualRankingResponse
import catcheat.android.owner.data.service.ranking.VisualRankingService
import retrofit2.Response
import javax.inject.Inject

class VisualRankingRemoteDataSourceImpl @Inject constructor(
    private val rankingService: VisualRankingService
) : VisualRankingRemoteDataSource {

    override suspend fun fetchVisualRanking(): Response<List<VisualRankingResponse>> {
        return rankingService.fetchVisualRanking()
    }
}