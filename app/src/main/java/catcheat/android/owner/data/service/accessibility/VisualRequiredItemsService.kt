package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.VisualRequiredItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface VisualRequiredItemsService {

    @GET("/api/store/visual/requirements-status/")
    suspend fun getVisualRequiredItems(
        @Header("Authorization") token: String
    ): Response<VisualRequiredItemsResponse> // BaseApiResponse 대신 Response<T> 사용
}