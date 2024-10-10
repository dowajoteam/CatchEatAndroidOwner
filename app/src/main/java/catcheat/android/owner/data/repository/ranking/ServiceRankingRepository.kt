package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import retrofit2.Response

interface ServiceRankingRepository {
    suspend fun fetchServiceRanking(): Response<List<ServiceRankingResponse>>
}