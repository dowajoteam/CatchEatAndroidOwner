package catcheat.android.owner.data.model.intro

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("token")
    val token: String? = null,

    @SerializedName("error")
    val error: String? = null
)