package com.euzhene.homescreen.domain

interface HomeRepo {
    suspend fun getProducts(): Result<Products>

    fun getCategories():Array<ProductCategory>

    fun getFilters():List<FilterProduct>
}