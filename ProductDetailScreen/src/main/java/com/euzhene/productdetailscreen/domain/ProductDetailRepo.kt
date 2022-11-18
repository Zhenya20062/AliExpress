package com.euzhene.productdetailscreen.domain

interface ProductDetailRepo {
    suspend fun getProductDetails(): Result<ProductDetail>
}