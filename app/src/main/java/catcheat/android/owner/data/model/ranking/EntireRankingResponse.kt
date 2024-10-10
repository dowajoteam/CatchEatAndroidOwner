package catcheat.android.owner.data.model.ranking

import com.google.gson.annotations.SerializedName

data class EntireRankingResponse(
    @SerializedName("rank")
    val rank: Int,

    @SerializedName("store_id")
    val storeId: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("physical_accessibility")
    val physicalAccessibility: Boolean,

    @SerializedName("service_accessibility")
    val serviceAccessibility: Boolean,

    @SerializedName("visual_impairment_accessibility")
    val visualImpairmentAccessibility: Boolean
)