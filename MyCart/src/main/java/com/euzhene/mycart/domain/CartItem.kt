package com.euzhene.mycart.domain

import com.google.gson.annotations.SerializedName

data class CartItem(
    val id:Int,
    @SerializedName("images") val image:String,
    val price:Float,
    val title:String,
    val count:Int = 1,
)
