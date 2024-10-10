package catcheat.android.owner.data.source.ranking

import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import catcheat.android.owner.data.service.ranking.EntireRankingService
import retrofit2.Response
import javax.inject.Inject

class EntireRankingRemoteDataSourceImpl @Inject constructor(
    private val rankingService: EntireRankingService
) : EntireRankingRemoteDataSource {

    override suspend fun fetchEntireRanking(): Response<List<EntireRankingResponse>> {
        return rankingService.fetchEntireRanking()
    }
}