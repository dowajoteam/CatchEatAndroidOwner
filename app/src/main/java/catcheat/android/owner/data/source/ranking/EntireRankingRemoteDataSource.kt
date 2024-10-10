package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import retrofit2.Response

interface EntireRankingRemoteDataSource {
    suspend fun fetchEntireRanking(): Response<List<EntireRankingResponse>>
}