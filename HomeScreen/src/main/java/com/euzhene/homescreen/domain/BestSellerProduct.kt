package com.euzhene.homescreen.domain

import com.google.gson.annotations.SerializedName

data class BestSellerProduct(
    val id:String,
    @SerializedName("is_favorites") val isFavorite: Boolean,
    val title: String,
    @SerializedName("price_without_discount") val discountPrice: Float ,
    @SerializedName("discount_price") val priceWithoutDiscount: Float,
    val picture: String,
    )
