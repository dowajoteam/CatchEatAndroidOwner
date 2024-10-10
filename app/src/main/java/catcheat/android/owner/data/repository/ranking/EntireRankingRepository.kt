package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import retrofit2.Response

interface EntireRankingRepository {
    suspend fun fetchEntireRanking(): Response<List<EntireRankingResponse>>
}