package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface VisualInfoService {

    @GET("/api/store/visual/accessibility/")
    suspend fun getVisualInfo(
        @Header("Authorization") token: String
    ): Response<VisualInfoResponse>
}