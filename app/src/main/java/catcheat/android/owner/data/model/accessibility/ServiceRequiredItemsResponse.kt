package catcheat.android.owner.data.model.accessibility

import com.google.gson.annotations.SerializedName

data class ServiceRequiredItemsResponse(
    @SerializedName("staff_call_order_available")
    val staffCallOrderAvailable: Boolean,
)
