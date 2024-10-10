package catcheat.android.owner.data.di.ranking

import catcheat.android.owner.data.repository.ranking.ServiceRankingRepository
import catcheat.android.owner.data.repository.ranking.ServiceRankingRepositoryImpl
import catcheat.android.owner.data.service.ranking.ServiceRankingService
import catcheat.android.owner.data.source.ranking.ServiceRankingRemoteDataSource
import catcheat.android.owner.data.source.ranking.ServiceRankingRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceRankingModule {

    // ServiceRankingService 제공
    @Provides
    @Singleton
    fun provideServiceRankingService(retrofit: Retrofit): ServiceRankingService {
        return retrofit.create(ServiceRankingService::class.java)
    }

    // ServiceRankingRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideServiceRankingRemoteDataSource(
        service: ServiceRankingService
    ): ServiceRankingRemoteDataSource {
        return ServiceRankingRemoteDataSourceImpl(service)
    }

    // ServiceRankingRepository 제공
    @Provides
    @Singleton
    fun provideServiceRankingRepository(
        remoteDataSource: ServiceRankingRemoteDataSource
    ): ServiceRankingRepository {
        return ServiceRankingRepositoryImpl(remoteDataSource)
    }
}
