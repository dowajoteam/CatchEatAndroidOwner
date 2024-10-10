package catcheat.android.owner.data.service.ranking

import catcheat.android.owner.data.model.ranking.VisualRankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface VisualRankingService {
    @GET("/api/store/rankings/visual/")
    suspend fun fetchVisualRanking(): Response<List<VisualRankingResponse>>
}