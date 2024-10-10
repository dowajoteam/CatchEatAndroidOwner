package catcheat.android.owner.data.di.ranking

import catcheat.android.owner.data.repository.ranking.RankingDetailsRepository
import catcheat.android.owner.data.repository.ranking.RankingDetailsRepositoryImpl
import catcheat.android.owner.data.service.ranking.RankingDetailsService
import catcheat.android.owner.data.source.ranking.RankingDetailsRemoteDataSource
import catcheat.android.owner.data.source.ranking.RankingDetailsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RankingDetailsModule {

    // RankingDetailsService 제공
    @Provides
    @Singleton
    fun provideRankingDetailsService(retrofit: Retrofit): RankingDetailsService {
        return retrofit.create(RankingDetailsService::class.java)
    }

    // RankingDetailsRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideRankingDetailsRemoteDataSource(
        service: RankingDetailsService
    ): RankingDetailsRemoteDataSource {
        return RankingDetailsRemoteDataSourceImpl(service)
    }

    // RankingDetailsRepository 제공
    @Provides
    @Singleton
    fun provideRankingDetailsRepository(
        remoteDataSource: RankingDetailsRemoteDataSource
    ): RankingDetailsRepository {
        return RankingDetailsRepositoryImpl(remoteDataSource)
    }
}
