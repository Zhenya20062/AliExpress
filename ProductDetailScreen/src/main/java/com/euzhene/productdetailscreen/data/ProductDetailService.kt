package com.euzhene.productdetailscreen.data

import com.euzhene.productdetailscreen.domain.ProductDetail
import retrofit2.Response
import retrofit2.http.GET

interface ProductDetailService {
    @GET(".")
   suspend fun getProductDetails(): Response<ProductDetail>
}