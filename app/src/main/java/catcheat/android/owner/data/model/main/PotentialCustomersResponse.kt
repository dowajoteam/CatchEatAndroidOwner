package catcheat.android.owner.data.model.main

import com.google.gson.annotations.SerializedName

data class PotentialCustomersResponse(
    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("total_potential_customers")
    val totalPotentialCustomers: Int
)