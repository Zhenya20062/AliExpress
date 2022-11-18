package com.euzhene.mycart.data

import com.euzhene.mycart.domain.CartInfo
import com.euzhene.mycart.domain.CartRepo
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val cartService: CartService,
) : CartRepo {
    override suspend fun getCartInfo(): Result<CartInfo> {
        return try {
            val response = cartService.getCartItems()
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.success(result)
            } else {
                Result.failure(Exception(response.errorBody().toString()))
            }


        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}