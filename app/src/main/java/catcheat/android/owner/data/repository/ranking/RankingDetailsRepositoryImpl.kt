package catcheat.android.owner.data.repository.ranking

import catcheat.android.owner.data.model.ranking.RankingDetailsResponse
import catcheat.android.owner.data.source.ranking.RankingDetailsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RankingDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RankingDetailsRemoteDataSource
) : RankingDetailsRepository {

    override suspend fun getRankingDetails(storeId: Int): Response<RankingDetailsResponse> {
        return remoteDataSource.getRankingDetails(storeId)
    }
}