package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import retrofit2.Response

interface RankingDetailsRemoteDataSource {
    suspend fun getRankingDetails(storeId: Int): Response<RankingDetailsResponse>
}