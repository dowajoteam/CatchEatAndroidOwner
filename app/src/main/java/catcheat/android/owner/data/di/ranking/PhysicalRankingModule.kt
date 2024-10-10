package catcheat.android.owner.data.di.ranking

import catcheat.android.owner.data.repository.ranking.PhysicalRankingRepository
import catcheat.android.owner.data.repository.ranking.PhysicalRankingRepositoryImpl
import catcheat.android.owner.data.service.ranking.PhysicalRankingService
import catcheat.android.owner.data.source.ranking.PhysicalRankingRemoteDataSource
import catcheat.android.owner.data.source.ranking.PhysicalRankingRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhysicalRankingModule {

    // PhysicalRankingService 제공
    @Provides
    @Singleton
    fun providePhysicalRankingService(retrofit: Retrofit): PhysicalRankingService {
        return retrofit.create(PhysicalRankingService::class.java)
    }

    // PhysicalRankingRemoteDataSource 제공
    @Provides
    @Singleton
    fun providePhysicalRankingRemoteDataSource(
        service: PhysicalRankingService
    ): PhysicalRankingRemoteDataSource {
        return PhysicalRankingRemoteDataSourceImpl(service)
    }

    // PhysicalRankingRepository 제공
    @Provides
    @Singleton
    fun providePhysicalRankingRepository(
        remoteDataSource: PhysicalRankingRemoteDataSource
    ): PhysicalRankingRepository {
        return PhysicalRankingRepositoryImpl(remoteDataSource)
    }
}
