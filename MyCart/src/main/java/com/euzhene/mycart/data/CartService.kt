package com.euzhene.mycart.data

import com.euzhene.mycart.domain.CartInfo
import retrofit2.Response
import retrofit2.http.GET

interface CartService {
@GET(".")
suspend fun getCartItems():Response<CartInfo>
}