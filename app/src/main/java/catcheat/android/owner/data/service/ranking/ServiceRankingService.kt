package catcheat.android.owner.data.service.ranking

import catcheat.android.owner.data.model.ranking.ServiceRankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceRankingService {
    @GET("/api/store/rankings/service/")
    suspend fun fetchServiceRanking(): Response<List<ServiceRankingResponse>>
}