package catcheat.android.owner.data.di.ranking

import catcheat.android.owner.data.repository.ranking.EntireRankingRepository
import catcheat.android.owner.data.repository.ranking.EntireRankingRepositoryImpl
import catcheat.android.owner.data.service.ranking.EntireRankingService
import catcheat.android.owner.data.source.ranking.EntireRankingRemoteDataSource
import catcheat.android.owner.data.source.ranking.EntireRankingRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EntireRankingModule {

    // EntireRankingService 제공
    @Provides
    @Singleton
    fun provideEntireRankingService(retrofit: Retrofit): EntireRankingService {
        return retrofit.create(EntireRankingService::class.java)
    }

    // EntireRankingRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideEntireRankingRemoteDataSource(
        service: EntireRankingService
    ): EntireRankingRemoteDataSource {
        return EntireRankingRemoteDataSourceImpl(service)
    }

    // EntireRankingRepository 제공
    @Provides
    @Singleton
    fun provideEntireRankingRepository(
        remoteDataSource: EntireRankingRemoteDataSource
    ): EntireRankingRepository {
        return EntireRankingRepositoryImpl(remoteDataSource)
    }
}
