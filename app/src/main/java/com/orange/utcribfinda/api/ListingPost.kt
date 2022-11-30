package com.orange.utcribfinda.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListingPost (
    @SerializedName("floorPlanName")
    @Expose
    var floorPlanName: String? = null,

    @SerializedName("bedBath")
    @Expose
    var bedBath: String? = null,

    @SerializedName("price")
    @Expose
    var price: String? = null,

    @SerializedName("sqFt")
    @Expose
    var sqFt: String? = null,

    @SerializedName("availability")
    @Expose
    var availability: String? = null
)