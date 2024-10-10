package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import retrofit2.Response

interface RankingDetailsRepository {
    suspend fun getRankingDetails(storeId: Int): Response<RankingDetailsResponse>
}
