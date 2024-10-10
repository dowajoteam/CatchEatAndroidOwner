package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import retrofit2.Response

interface ServiceRankingRemoteDataSource {
    suspend fun fetchServiceRanking(): Response<List<ServiceRankingResponse>>
}
