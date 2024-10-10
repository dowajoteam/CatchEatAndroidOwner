package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.VisualRankingResponse
import catcheat.android.owner.data.source.ranking.VisualRankingRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class VisualRankingRepositoryImpl @Inject constructor(
    private val remoteDataSource: VisualRankingRemoteDataSource
) : VisualRankingRepository {

    override suspend fun fetchVisualRanking(): Response<List<VisualRankingResponse>> {
        return remoteDataSource.fetchVisualRanking()
    }
}