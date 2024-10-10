package catcheat.android.owner.data.model.intro

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("password")
    val password: String? = null,

    @SerializedName("store_name")
    val storeName: String? = null,

    @SerializedName("business_type")
    val businessType: String? = null,

    @SerializedName("phone_number")
    val phoneNumber: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("error")
    val error: String? = null
)