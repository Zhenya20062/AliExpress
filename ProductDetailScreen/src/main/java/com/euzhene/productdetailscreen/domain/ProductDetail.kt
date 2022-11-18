package com.euzhene.productdetailscreen.domain

import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("CPU") val cpu: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>,
    @SerializedName("isFavorites") val isFavorite: Boolean,
    val price: Float,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String,
):java.io.Serializable
