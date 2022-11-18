package com.euzhene.homescreen.domain

import com.google.gson.annotations.SerializedName

data class HotProduct(
    val id:String,
    @SerializedName("is_new") val isNew: Boolean,
    val title: String,
    @SerializedName("subtitle") val description: String,
    val picture: String,
    @SerializedName("is_buy") val isBuy: Boolean,
)
