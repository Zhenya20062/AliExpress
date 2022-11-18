package com.euzhene.homescreen.domain

import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repo: HomeRepo) {
    operator fun invoke(): Array<ProductCategory> {
        return repo.getCategories()
    }
}