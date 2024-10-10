package catcheat.android.owner.data.model.accessibility

import com.google.gson.annotations.SerializedName

data class VisualInfoResponse(
    @SerializedName("guide_dog_allowed_description")
    val guideDogAllowedDescription: String?,

    @SerializedName("guide_dog_allowed_image")
    val guideDogAllowedImage: String?,

    @SerializedName("self_service_available_description")
    val selfServiceAvailableDescription: String?,

    @SerializedName("self_service_available_image")
    val selfServiceAvailableImage: String?,

    @SerializedName("central_table_setup_description")
    val centralTableSetupDescription: String?,

    @SerializedName("central_table_setup_image")
    val centralTableSetupImage: String?
)
