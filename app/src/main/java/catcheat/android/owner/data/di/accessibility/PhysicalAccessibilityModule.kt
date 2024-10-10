package catcheat.android.owner.data.di.accessibility

import catcheat.android.owner.data.repository.accessibility.PhysicalInfoRepository
import catcheat.android.owner.data.repository.accessibility.PhysicalInfoRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.PhysicalInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.PhysicalInfoUploadRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.PhysicalRequiredItemsRepository
import catcheat.android.owner.data.repository.accessibility.PhysicalRequiredItemsRepositoryImpl
import catcheat.android.owner.data.service.accessibility.PhysicalInfoService
import catcheat.android.owner.data.service.accessibility.PhysicalInfoUploadService
import catcheat.android.owner.data.service.accessibility.PhysicalRequiredItemsService
import catcheat.android.owner.data.source.accessibility.PhysicalInfoRemoteDataSource
import catcheat.android.owner.data.source.accessibility.PhysicalInfoRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.PhysicalInfoUploadRemoteDataSource
import catcheat.android.owner.data.source.accessibility.PhysicalInfoUploadRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.PhysicalRequiredItemsRemoteDataSource
import catcheat.android.owner.data.source.accessibility.PhysicalRequiredItemsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhysicalAccessibilityModule {

    // PhysicalInfoService 제공
    @Provides
    @Singleton
    fun providePhysicalInfoService(retrofit: Retrofit): PhysicalInfoService {
        return retrofit.create(PhysicalInfoService::class.java)
    }

    // PhysicalInfoUploadService 제공
    @Provides
    @Singleton
    fun providePhysicalInfoUploadService(retrofit: Retrofit): PhysicalInfoUploadService {
        return retrofit.create(PhysicalInfoUploadService::class.java)
    }

    // PhysicalRequiredItemsService 제공
    @Provides
    @Singleton
    fun providePhysicalRequiredItemsService(retrofit: Retrofit): PhysicalRequiredItemsService {
        return retrofit.create(PhysicalRequiredItemsService::class.java)
    }

    // PhysicalInfoRemoteDataSource 제공
    @Provides
    @Singleton
    fun providePhysicalInfoRemoteDataSource(
        service: PhysicalInfoService
    ): PhysicalInfoRemoteDataSource {
        return PhysicalInfoRemoteDataSourceImpl(service)
    }

    // PhysicalInfoUploadRemoteDataSource 제공
    @Provides
    @Singleton
    fun providePhysicalInfoUploadRemoteDataSource(
        service: PhysicalInfoUploadService
    ): PhysicalInfoUploadRemoteDataSource {
        return PhysicalInfoUploadRemoteDataSourceImpl(service)
    }

    // PhysicalRequiredItemsRemoteDataSource 제공
    @Provides
    @Singleton
    fun providePhysicalRequiredItemsRemoteDataSource(
        service: PhysicalRequiredItemsService
    ): PhysicalRequiredItemsRemoteDataSource {
        return PhysicalRequiredItemsRemoteDataSourceImpl(service)
    }

    // PhysicalInfoRepository 제공
    @Provides
    @Singleton
    fun providePhysicalInfoRepository(
        remoteDataSource: PhysicalInfoRemoteDataSource
    ): PhysicalInfoRepository {
        return PhysicalInfoRepositoryImpl(remoteDataSource)
    }

    // PhysicalInfoUploadRepository 제공
    @Provides
    @Singleton
    fun providePhysicalInfoUploadRepository(
        remoteDataSource: PhysicalInfoUploadRemoteDataSource
    ): PhysicalInfoUploadRepository {
        return PhysicalInfoUploadRepositoryImpl(remoteDataSource)
    }

    // PhysicalRequiredItemsRepository 제공
    @Provides
    @Singleton
    fun providePhysicalRequiredItemsRepository(
        remoteDataSource: PhysicalRequiredItemsRemoteDataSource
    ): PhysicalRequiredItemsRepository {
        return PhysicalRequiredItemsRepositoryImpl(remoteDataSource)
    }
}