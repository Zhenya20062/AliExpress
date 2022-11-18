package com.euzhene.productdetailscreen.domain

import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor (private val repo: ProductDetailRepo) {
    suspend operator fun invoke(): Result<ProductDetail> {
        return repo.getProductDetails()
    }
}