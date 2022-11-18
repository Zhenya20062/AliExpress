package com.euzhene.mycart.domain

import com.google.gson.annotations.SerializedName

data class CartInfo(
    @SerializedName("basket") val cartItems:List<CartItem>,
    val delivery:String,
    val id:Int,
    val total:Float,
)
