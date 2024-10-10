package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import retrofit2.Response

interface PhysicalRankingRemoteDataSource {
    suspend fun fetchPhysicalRanking(): Response<List<PhysicalRankingResponse>>
}
