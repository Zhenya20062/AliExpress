package com.euzhene.productdetailscreen.present

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euzhene.productdetailscreen.domain.GetProductDetailsUseCase
import com.euzhene.productdetailscreen.domain.ProductDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel() {
    private val _productsDetail = MutableStateFlow<ProductDetail?>(null)
    val productDetailList = _productsDetail.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    fun getProductsDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getProductDetailsUseCase()
            if (res.isFailure) {
                _error.emit(res.exceptionOrNull().toString())
            } else {
                _productsDetail.emit(
                    res.getOrThrow().copy(images = res.getOrThrow().images.toMutableList().apply {
                        addAll(res.getOrThrow().images)
                    }
                    ))
            }

        }
    }
}