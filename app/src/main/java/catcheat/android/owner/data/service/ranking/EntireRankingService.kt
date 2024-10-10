package catcheat.android.owner.data.service.ranking

import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface EntireRankingService {
    @GET("/api/store/rankings/entire/")
    suspend fun fetchEntireRanking(): Response<List<EntireRankingResponse>>
}