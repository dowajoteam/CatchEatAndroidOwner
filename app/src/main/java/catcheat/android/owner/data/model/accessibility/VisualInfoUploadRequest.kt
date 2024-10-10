package catcheat.android.owner.data.model.accessibility

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class VisualInfoUploadRequest(
    val guideDogAllowedDescription: RequestBody? = null,
    val guideDogAllowedImage: MultipartBody.Part? = null,

    val selfServiceAvailableDescription: RequestBody? = null,
    val selfServiceAvailableImage: MultipartBody.Part? = null,

    val centralTableSetupDescription: RequestBody? = null,
    val centralTableSetupImage: MultipartBody.Part? = null
)
