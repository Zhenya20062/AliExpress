package com.euzhene.productdetailscreen.data

import com.euzhene.productdetailscreen.domain.ProductDetail
import com.euzhene.productdetailscreen.domain.ProductDetailRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDetailRepoImpl @Inject constructor(
    private val service: ProductDetailService,
) : ProductDetailRepo {
    override suspend fun getProductDetails(): Result<ProductDetail> {
        return try {
            val response = service.getProductDetails()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}