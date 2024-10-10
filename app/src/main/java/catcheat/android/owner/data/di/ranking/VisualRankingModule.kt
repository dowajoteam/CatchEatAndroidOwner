package catcheat.android.owner.data.di.ranking

import catcheat.android.owner.data.repository.ranking.VisualRankingRepository
import catcheat.android.owner.data.repository.ranking.VisualRankingRepositoryImpl
import catcheat.android.owner.data.service.ranking.VisualRankingService
import catcheat.android.owner.data.source.ranking.VisualRankingRemoteDataSource
import catcheat.android.owner.data.source.ranking.VisualRankingRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VisualRankingModule {

    // VisualRankingService 제공
    @Provides
    @Singleton
    fun provideVisualRankingService(retrofit: Retrofit): VisualRankingService {
        return retrofit.create(VisualRankingService::class.java)
    }

    // VisualRankingRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideVisualRankingRemoteDataSource(
        service: VisualRankingService
    ): VisualRankingRemoteDataSource {
        return VisualRankingRemoteDataSourceImpl(service)
    }

    // VisualRankingRepository 제공
    @Provides
    @Singleton
    fun provideVisualRankingRepository(
        remoteDataSource: VisualRankingRemoteDataSource
    ): VisualRankingRepository {
        return VisualRankingRepositoryImpl(remoteDataSource)
    }
}
