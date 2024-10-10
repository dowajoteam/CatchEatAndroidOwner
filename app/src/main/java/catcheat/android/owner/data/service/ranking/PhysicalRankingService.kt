package catcheat.android.owner.data.service.ranking

import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhysicalRankingService {
    @GET("/api/store/rankings/physical/")
    suspend fun fetchPhysicalRanking(): Response<List<PhysicalRankingResponse>>
}