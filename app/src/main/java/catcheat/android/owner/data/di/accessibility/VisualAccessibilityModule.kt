package catcheat.android.owner.data.di.accessibility

import catcheat.android.owner.data.repository.accessibility.VisualInfoRepository
import catcheat.android.owner.data.repository.accessibility.VisualInfoRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.VisualInfoUploadRepository
import catcheat.android.owner.data.repository.accessibility.VisualInfoUploadRepositoryImpl
import catcheat.android.owner.data.repository.accessibility.VisualRequiredItemsRepository
import catcheat.android.owner.data.repository.accessibility.VisualRequiredItemsRepositoryImpl
import catcheat.android.owner.data.service.accessibility.VisualInfoService
import catcheat.android.owner.data.service.accessibility.VisualInfoUploadService
import catcheat.android.owner.data.service.accessibility.VisualRequiredItemsService
import catcheat.android.owner.data.source.accessibility.VisualInfoRemoteDataSource
import catcheat.android.owner.data.source.accessibility.VisualInfoRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.VisualInfoUploadRemoteDataSource
import catcheat.android.owner.data.source.accessibility.VisualInfoUploadRemoteDataSourceImpl
import catcheat.android.owner.data.source.accessibility.VisualRequiredItemsRemoteDataSource
import catcheat.android.owner.data.source.accessibility.VisualRequiredItemsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VisualAccessibilityModule {

    // VisualInfoService 제공
    @Provides
    @Singleton
    fun provideVisualInfoService(retrofit: Retrofit): VisualInfoService {
        return retrofit.create(VisualInfoService::class.java)
    }

    // VisualInfoUploadService 제공
    @Provides
    @Singleton
    fun provideVisualInfoUploadService(retrofit: Retrofit): VisualInfoUploadService {
        return retrofit.create(VisualInfoUploadService::class.java)
    }

    // VisualRequiredItemsService 제공
    @Provides
    @Singleton
    fun provideVisualRequiredItemsService(retrofit: Retrofit): VisualRequiredItemsService {
        return retrofit.create(VisualRequiredItemsService::class.java)
    }

    // VisualInfoRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideVisualInfoRemoteDataSource(
        service: VisualInfoService
    ): VisualInfoRemoteDataSource {
        return VisualInfoRemoteDataSourceImpl(service)
    }

    // VisualInfoUploadRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideVisualInfoUploadRemoteDataSource(
        service: VisualInfoUploadService
    ): VisualInfoUploadRemoteDataSource {
        return VisualInfoUploadRemoteDataSourceImpl(service)
    }

    // VisualRequiredItemsRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideVisualRequiredItemsRemoteDataSource(
        service: VisualRequiredItemsService
    ): VisualRequiredItemsRemoteDataSource {
        return VisualRequiredItemsRemoteDataSourceImpl(service)
    }

    // VisualInfoRepository 제공
    @Provides
    @Singleton
    fun provideVisualInfoRepository(
        remoteDataSource: VisualInfoRemoteDataSource
    ): VisualInfoRepository {
        return VisualInfoRepositoryImpl(remoteDataSource)
    }

    // VisualInfoUploadRepository 제공
    @Provides
    @Singleton
    fun provideVisualInfoUploadRepository(
        remoteDataSource: VisualInfoUploadRemoteDataSource
    ): VisualInfoUploadRepository {
        return VisualInfoUploadRepositoryImpl(remoteDataSource)
    }

    // VisualRequiredItemsRepository 제공
    @Provides
    @Singleton
    fun provideVisualRequiredItemsRepository(
        remoteDataSource: VisualRequiredItemsRemoteDataSource
    ): VisualRequiredItemsRepository {
        return VisualRequiredItemsRepositoryImpl(remoteDataSource)
    }
}
