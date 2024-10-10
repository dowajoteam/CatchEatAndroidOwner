package catcheat.android.owner.data.model.main

import okhttp3.MultipartBody

data class StoreImageUpdateRequest(
    val image: MultipartBody.Part
)