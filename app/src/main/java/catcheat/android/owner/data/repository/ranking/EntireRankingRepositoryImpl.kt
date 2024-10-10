package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.EntireRankingResponse
import catcheat.android.owner.data.source.ranking.EntireRankingRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class EntireRankingRepositoryImpl @Inject constructor(
    private val remoteDataSource: EntireRankingRemoteDataSource
) : EntireRankingRepository {

    override suspend fun fetchEntireRanking(): Response<List<EntireRankingResponse>> {
        return remoteDataSource.fetchEntireRanking()
    }
}
