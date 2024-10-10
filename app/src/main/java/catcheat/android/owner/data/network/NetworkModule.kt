package catcheat.android.owner.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)  // AuthInterceptor 추가
            .addInterceptor(logging)  // 로그 인터셉터 추가
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .client(httpClient)  // OkHttp 클라이언트 설정
            .addConverterFactory(GsonConverterFactory.create())  // JSON 응답 처리
            .build()
    }
}

