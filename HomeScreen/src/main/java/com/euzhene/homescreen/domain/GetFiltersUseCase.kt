package com.euzhene.homescreen.domain

import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(private val repo: HomeRepo) {
    operator fun invoke(): List<FilterProduct> {
        return repo.getFilters()
    }
}