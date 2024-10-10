package catcheat.android.owner.data.model.main

import com.google.gson.annotations.SerializedName

data class StoreImageUpdateResponse(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("detail")
    val detail: String? = null
)