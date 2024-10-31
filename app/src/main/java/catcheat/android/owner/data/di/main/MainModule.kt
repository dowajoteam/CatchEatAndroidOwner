package catcheat.android.owner.data.di.main

import catcheat.android.owner.data.repository.main.PotentialCustomersRepository
import catcheat.android.owner.data.repository.main.PotentialCustomersRepositoryImpl
import catcheat.android.owner.data.repository.main.StoreImageUpdateRepository
import catcheat.android.owner.data.repository.main.StoreImageUpdateRepositoryImpl
import catcheat.android.owner.data.repository.main.StoreInfoRepository
import catcheat.android.owner.data.repository.main.StoreInfoRepositoryImpl
import catcheat.android.owner.data.service.main.PotentialCustomersService
import catcheat.android.owner.data.service.main.StoreImageUpdateService
import catcheat.android.owner.data.service.main.StoreInfoService
import catcheat.android.owner.data.source.main.PotentialCustomersRemoteDataSource
import catcheat.android.owner.data.source.main.PotentialCustomersRemoteDataSourceImpl
import catcheat.android.owner.data.source.main.StoreImageUpdateRemoteDataSource
import catcheat.android.owner.data.source.main.StoreImageUpdateRemoteDataSourceImpl
import catcheat.android.owner.data.source.main.StoreInfoRemoteDataSource
import catcheat.android.owner.data.source.main.StoreInfoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    // StoreImageUpdateService 제공
    @Provides
    @Singleton
    fun provideStoreImageUpdateService(retrofit: Retrofit): StoreImageUpdateService {
        return retrofit.create(StoreImageUpdateService::class.java)
    }

    // StoreInfoService 제공
    @Provides
    @Singleton
    fun provideStoreInfoService(retrofit: Retrofit): StoreInfoService {
        return retrofit.create(StoreInfoService::class.java)
    }

    // PotentialCustomersService 제공
    @Provides
    @Singleton
    fun providePotentialCustomersService(retrofit: Retrofit): PotentialCustomersService {
        return retrofit.create(PotentialCustomersService::class.java)
    }

    // StoreImageUpdateRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideStoreImageUpdateRemoteDataSource(
        service: StoreImageUpdateService
    ): StoreImageUpdateRemoteDataSource {
        return StoreImageUpdateRemoteDataSourceImpl(service)
    }

    // StoreInfoRemoteDataSource 제공
    @Provides
    @Singleton
    fun provideStoreInfoRemoteDataSource(
        service: StoreInfoService
    ): StoreInfoRemoteDataSource {
        return StoreInfoRemoteDataSourceImpl(service)
    }

    // PotentialCustomersRemoteDataSource 제공
    @Provides
    @Singleton
    fun providePotentialCustomersRemoteDataSource(
        service: PotentialCustomersService
    ): PotentialCustomersRemoteDataSource {
        return PotentialCustomersRemoteDataSourceImpl(service)
    }

    // StoreImageUpdateRepository 제공
    @Provides
    @Singleton
    fun provideStoreImageUpdateRepository(
        remoteDataSource: StoreImageUpdateRemoteDataSource
    ): StoreImageUpdateRepository {
        return StoreImageUpdateRepositoryImpl(remoteDataSource)
    }

    // StoreInfoRepository 제공
    @Provides
    @Singleton
    fun provideStoreInfoRepository(
        remoteDataSource: StoreInfoRemoteDataSource
    ): StoreInfoRepository {
        return StoreInfoRepositoryImpl(remoteDataSource)
    }

    // PotentialCustomersRepository 제공
    @Provides
    @Singleton
    fun providePotentialCustomersRepository(
        remoteDataSource: PotentialCustomersRemoteDataSource
    ): PotentialCustomersRepository {
        return PotentialCustomersRepositoryImpl(remoteDataSource)
    }
}
