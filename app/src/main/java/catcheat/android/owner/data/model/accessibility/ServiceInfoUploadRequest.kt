package catcheat.android.owner.data.model.accessibility

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ServiceInfoUploadRequest(
    val staffCallOrderAvailableDescription: RequestBody? = null,
    val staffCallOrderAvailableImage: MultipartBody.Part? = null
)
