package catcheat.android.owner.data.network

import android.util.Log
import okhttp3.Interceptor
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val token = tokenProvider.getToken()
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        // 로그 추가 - 요청이 들어왔을 때의 상태
        Log.d("AuthInterceptor", "Intercepting request: ${originalRequest.url}")

        // 이미 Authorization 헤더가 추가되었는지 확인하고 없을 때만 추가
        val existingAuthHeader = originalRequest.header("Authorization")
        if (existingAuthHeader == null && !token.isNullOrEmpty()) {
            // Authorization 헤더가 없을 때 토큰 추가
            requestBuilder.addHeader("Authorization", "Token $token")
            Log.d("AuthInterceptor", "Token added to header: $token")
        } else if (existingAuthHeader != null) {
            // 이미 Authorization 헤더가 있을 때 그 값을 다시 확인
            Log.d("AuthInterceptor", "Authorization header already exists: $existingAuthHeader")
            if (existingAuthHeader != "Token $token") {
                // 기존 값과 토큰이 다르면 새로 설정
                requestBuilder.header("Authorization", "Token $token")
                Log.d("AuthInterceptor", "Updated Authorization header with new token: $token")
            }
        } else {
            Log.e("AuthInterceptor", "Token is missing or invalid!")
        }

        // 최종 요청 헤더 로그 출력
        Log.d("AuthInterceptor", "Request Headers: ${requestBuilder.build().headers}")

        return chain.proceed(requestBuilder.build())
    }
}




