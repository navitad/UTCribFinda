package com.orange.utcribfinda.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListingPost(

    @SerializedName("floorPlanName")
    @Expose
    var floorPlanName: String? = null,

    @SerializedName("numBeds")
    @Expose
    var numBeds: Int? = null,

    @SerializedName("numBaths")
    @Expose
    var numBaths: Int? = null,

    @SerializedName("sqFT")
    @Expose
    var sqFT: Int? = null,

    @SerializedName("price")
    @Expose
    var price: Int? = null
)