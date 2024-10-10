package catcheat.android.owner.data.di.accessibility

import catcheat.android.owner.data.repository.accessibility.ServiceInfoRepository
import catcheat.android.owner.data.repository.accessibility.ServiceInfoRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.ServiceInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.ServiceInfoUploadRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.ServiceRequiredItemsRepository
import catcheat.android.owner.data.repository.accessibility.ServiceRequiredItemsRepositoryImpl
import catcheat.android.owner.data.service.accessibility.ServiceInfoService
import catcheat.android.owner.data.service.accessibility.ServiceInfoUploadService
import catcheat.android.owner.data.service.accessibility.ServiceRequiredItemsService
import catcheat.android.owner.data.source.accessibility.ServiceInfoRemoteDataSource
import catcheat.android.owner.data.source.accessibility.ServiceInfoRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.ServiceInfoUploadRemoteDataSource
import catcheat.android.owner.data.source.accessibility.ServiceInfoUploadRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.ServiceRequiredItemsRemoteDataSource
import catcheat.android.owner.data.source.accessibility.ServiceRequiredItemsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceAccessibilityModule {

    // ServiceInfoService 제공
    @Provides
    @Singleton
    fun provideServiceInfoService(retrofit: Retrofit): ServiceInfoService {
        return retrofit.create(ServiceInfoService::class.java)
    }

    // ServiceInfoUploadService 제공
    @Provides
    @Singleton
    fun provideServiceInfoUploadService(retrofit: Retrofit): ServiceInfoUploadService {
        return retrofit.create(ServiceInfoUploadService::class.java)
    }

    // ServiceRequiredItemsService 제공
    @Provides
    @Singleton
    fun provideServiceRequiredItemsService(retrofit: Retrofit): ServiceRequiredItemsService {
        return retrofit.create(ServiceRequiredItemsService::class.java)
    }

    // ServiceInfoRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideServiceInfoRemoteDataSource(
        service: ServiceInfoService
    ): ServiceInfoRemoteDataSource {
        return ServiceInfoRemoteDataSourceImpl(service)
    }

    // ServiceInfoUploadRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideServiceInfoUploadRemoteDataSource(
        service: ServiceInfoUploadService
    ): ServiceInfoUploadRemoteDataSource {
        return ServiceInfoUploadRemoteDataSourceImpl(service)
    }

    // ServiceRequiredItemsRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideServiceRequiredItemsRemoteDataSource(
        service: ServiceRequiredItemsService
    ): ServiceRequiredItemsRemoteDataSource {
        return ServiceRequiredItemsRemoteDataSourceImpl(service)
    }

    // ServiceInfoRepository 제공
    @Provides
    @Singleton
    fun provideServiceInfoRepository(
        remoteDataSource: ServiceInfoRemoteDataSource
    ): ServiceInfoRepository {
        return ServiceInfoRepositoryImpl(remoteDataSource)
    }

    // ServiceInfoUploadRepository 제공
    @Provides
    @Singleton
    fun provideServiceInfoUploadRepository(
        remoteDataSource: ServiceInfoUploadRemoteDataSource
    ): ServiceInfoUploadRepository {
        return ServiceInfoUploadRepositoryImpl(remoteDataSource)
    }

    // ServiceRequiredItemsRepository 제공
    @Provides
    @Singleton
    fun provideServiceRequiredItemsRepository(
        remoteDataSource: ServiceRequiredItemsRemoteDataSource
    ): ServiceRequiredItemsRepository {
        return ServiceRequiredItemsRepositoryImpl(remoteDataSource)
    }
}
