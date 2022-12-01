package com.orange.utcribfinda.api

import com.google.gson.annotations.SerializedName

data class ListingPost(

    @SerializedName("floorPlanName")
    val floorPlanName: String,

    @SerializedName("numBeds")
    val numBeds: Int? = 0,

    @SerializedName("numBaths")
    val numBaths: Int,

    @SerializedName("sqFT")
    val sqFT: Int? = 0,

    @SerializedName("price")
    val price: Int
)
