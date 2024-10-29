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
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)    // AuthInterceptor 추가
            .addInterceptor(logging)    // 로그 인터셉터 추가
            .connectTimeout(30, TimeUnit.SECONDS)  // 연결 타임아웃 설정
            .readTimeout(30, TimeUnit.SECONDS)     // 읽기 타임아웃 설정
            .writeTimeout(30, TimeUnit.SECONDS)    // 쓰기 타임아웃 설정
            .retryOnConnectionFailure(false)    // 재시도 비활성화
            .build()

        return Retrofit.Builder()
            .baseUrl("https://port-0-catcheat-m293kryub1f70625.sel4.cloudtype.app")
            .client(httpClient)  // OkHttp 클라이언트 설정
            .addConverterFactory(GsonConverterFactory.create())  // JSON 응답 처리
            .build()
    }
}

