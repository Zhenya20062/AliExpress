package com.euzhene.homescreen.data

import com.euzhene.homescreen.domain.Products
import retrofit2.Response
import retrofit2.http.GET


interface ProductService {
    @GET(".")
    suspend fun getProducts(): Response<Products>
}