package com.euzhene.mycart.domain

interface CartRepo {
    suspend fun getCartInfo(): Result<CartInfo>
}