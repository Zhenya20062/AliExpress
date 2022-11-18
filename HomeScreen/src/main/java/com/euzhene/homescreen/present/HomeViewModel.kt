package com.euzhene.homescreen.present

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euzhene.homescreen.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    getCategoriesUseCase: GetCategoriesUseCase,
    getFiltersUseCase: GetFiltersUseCase,
) : ViewModel() {
    private val _hotProducts = MutableStateFlow<List<HotProduct>?>(null)
    val hotProducts = _hotProducts.asStateFlow()

    private val _bestSellerProducts = MutableStateFlow<List<BestSellerProduct>?>(null)
    val bestSellerProducts = _bestSellerProducts.asStateFlow()

    val categories = getCategoriesUseCase()
    val filters = getFiltersUseCase()

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getProductsUseCase()
            if (result.isFailure) {
                _error.emit(result.exceptionOrNull()?.localizedMessage.toString())
            } else {
                val products = result.getOrThrow()
                _hotProducts.emit(products.hotProducts)
                _bestSellerProducts.emit(products.bestSellerProducts)
            }
        }
    }

}