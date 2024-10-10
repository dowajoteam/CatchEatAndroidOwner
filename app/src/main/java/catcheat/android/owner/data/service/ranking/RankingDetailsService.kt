package catcheat.android.owner.data.service.ranking

import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RankingDetailsService {
    @GET("/api/store/rankings/{store_id}")
    suspend fun getRankingDetails(
        @Path("store_id") storeId: Int
    ): Response<RankingDetailsResponse>
}