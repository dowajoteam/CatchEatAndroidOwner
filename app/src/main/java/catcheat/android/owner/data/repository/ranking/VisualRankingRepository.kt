package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.VisualRankingResponse
import retrofit2.Response

interface VisualRankingRepository {
    suspend fun fetchVisualRanking(): Response<List<VisualRankingResponse>>
}
