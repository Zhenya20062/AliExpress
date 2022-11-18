package com.euzhene.homescreen.domain

import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo: HomeRepo
) {
    suspend operator fun invoke(): Result<Products> {
        return repo.getProducts()
    }
}