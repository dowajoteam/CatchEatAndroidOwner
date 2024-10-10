package catcheat.android.owner.data.model.accessibility

import com.google.gson.annotations.SerializedName

data class VisualRequiredItemsResponse(
    @SerializedName("guide_dog_allowed")
    val guideDogAllowed: Boolean,

    @SerializedName("self_service_available")
    val selfServiceAvailable: Boolean,

    @SerializedName("central_table_setup")
    val centralTableSetup: Boolean
)