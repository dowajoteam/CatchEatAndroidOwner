package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.PhysicalRankingResponse
import catcheat.android.owner.data.source.ranking.PhysicalRankingRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PhysicalRankingRepositoryImpl @Inject constructor(
    private val remoteDataSource: PhysicalRankingRemoteDataSource
) : PhysicalRankingRepository {

    override suspend fun fetchPhysicalRanking(): Response<List<PhysicalRankingResponse>> {
        return remoteDataSource.fetchPhysicalRanking()
    }
}