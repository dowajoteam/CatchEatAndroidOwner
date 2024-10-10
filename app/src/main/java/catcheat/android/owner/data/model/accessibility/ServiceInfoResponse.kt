package catcheat.android.owner.data.model.accessibility

import com.google.gson.annotations.SerializedName

data class ServiceInfoResponse(
    @SerializedName("staff_call_order_available_description")
    val staffCallOrderAvailableDescription: String,

    @SerializedName("staff_call_order_available_image")
    val staffCallOrderAvailableImage: String?
)