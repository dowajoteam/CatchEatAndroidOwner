package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.VisualRankingResponse
import retrofit2.Response

interface VisualRankingRemoteDataSource {
    suspend fun fetchVisualRanking(): Response<List<VisualRankingResponse>>
}
