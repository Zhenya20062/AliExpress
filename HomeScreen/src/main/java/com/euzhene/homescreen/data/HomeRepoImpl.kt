package com.euzhene.homescreen.data

import android.util.Log
import com.euzhene.homescreen.R
import com.euzhene.homescreen.domain.FilterProduct
import com.euzhene.homescreen.domain.HomeRepo
import com.euzhene.homescreen.domain.ProductCategory
import com.euzhene.homescreen.domain.Products
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepoImpl @Inject constructor(
    private val productService: ProductService,
) : HomeRepo {
    private val categoryList = arrayOf(
        ProductCategory(
            "Phones",
            R.drawable.ic_phone,
            true
        ),
        ProductCategory(
            "Computer",
            R.drawable.ic_pc,
            false
        ),
        ProductCategory(
            "Health",
            R.drawable.ic_health,
            false
        ),
        ProductCategory(
            "Books",
            R.drawable.ic_books,
            false
        ),
        ProductCategory(
            "Phones",
            R.drawable.ic_phone,
            false
        ),
        ProductCategory(
            "Computer",
            R.drawable.ic_pc,
            false
        ),
        ProductCategory(
            "Health",
            R.drawable.ic_health,
            false
        ),
        ProductCategory(
            "Books",
            R.drawable.ic_books,
            false
        ),
    )
    private val filterList = listOf(
        FilterProduct(
            title = "Brand",
            options = listOf("Samsung","Iphone")
        ),
        FilterProduct(
            title = "Price",
            options = listOf("$300-$500", "$501-$999", "$1000-$3999", "$4000-$10000")
        ),
        FilterProduct(
            title = "Size",
            options = listOf("4.5 to 5.5 inches")
        ),
    )

    override suspend fun getProducts(): Result<Products> {
        try {
            val response = productService.getProducts()
            if (!response.isSuccessful) {
                return Result.failure(Exception(response.errorBody().toString()))
            }

            return Result.success(response.body()!!)
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    override fun getCategories(): Array<ProductCategory> {
        return categoryList
    }

    override fun getFilters(): List<FilterProduct> {
        return filterList
    }
}