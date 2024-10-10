package catcheat.android.owner.data.di.intro

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import catcheat.android.owner.data.repository.intro.*
import catcheat.android.owner.data.source.intro.* // RemoteDataSource 관련 import
import catcheat.android.owner.data.service.intro.* // Service 관련 import
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IntroModule {

    // SignIn 관련 제공 메서드들
    @Provides
    @Singleton
    fun provideSignInRepository(
        repositoryImpl: SignInRepositoryImpl
    ): SignInRepository {
        return repositoryImpl
    }

    @Provides
    @Singleton
    fun provideSignInRemoteDataSource(
        remoteDataSourceImpl: SignInRemoteDataSourceImpl
    ): SignInRemoteDataSource {
        return remoteDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideSignInService(retrofit: Retrofit): SignInService {
        return retrofit.create(SignInService::class.java)
    }

    // SignUp 관련 제공 메서드들
    @Provides
    @Singleton
    fun provideSignUpRepository(
        repositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository {
        return repositoryImpl
    }

    @Provides
    @Singleton
    fun provideSignUpRemoteDataSource(
        remoteDataSourceImpl: SignUpRemoteDataSourceImpl
    ): SignUpRemoteDataSource {
        return remoteDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }
}
