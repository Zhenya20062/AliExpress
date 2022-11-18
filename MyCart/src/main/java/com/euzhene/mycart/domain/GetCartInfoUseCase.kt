package com.euzhene.mycart.domain

import javax.inject.Inject

class GetCartInfoUseCase @Inject constructor(
    private val repo: CartRepo
) {
    suspend operator fun invoke(): Result<CartInfo> {
        return repo.getCartInfo()
    }
}