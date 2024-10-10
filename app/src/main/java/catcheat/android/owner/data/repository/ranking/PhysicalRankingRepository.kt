package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import retrofit2.Response

interface PhysicalRankingRepository {
    suspend fun fetchPhysicalRanking(): Response<List<PhysicalRankingResponse>>
}