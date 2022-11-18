package com.euzhene.homescreen.domain

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("home_store") val hotProducts:List<HotProduct>,
    @SerializedName("best_seller") val bestSellerProducts:List<BestSellerProduct>,
)
