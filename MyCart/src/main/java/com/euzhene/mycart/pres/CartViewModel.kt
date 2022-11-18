package com.euzhene.mycart.pres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euzhene.mycart.domain.CartInfo
import com.euzhene.mycart.domain.CartItem
import com.euzhene.mycart.domain.GetCartInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartInfoUseCase: GetCartInfoUseCase,
) : ViewModel() {
    private val _cartInfo = MutableStateFlow<CartInfo?>(null)
    val cartInfo = _cartInfo.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    fun getCartInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCartInfoUseCase()
            if (res.isFailure) {
                _error.emit(res.exceptionOrNull().toString())
            } else {
                var info = res.getOrThrow()
                info = info.copy(cartItems = info.cartItems.map { it.copy(count = 1) })
                _cartInfo.emit(info)
            }
        }
    }
    fun increaseCartItemCount(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val info = _cartInfo.value!!
            val list = info.cartItems.toMutableList()
            list[list.indexOf(cartItem)] = cartItem.copy(count = cartItem.count + 1)

            updateCartInfo(info.copy(cartItems = list))
        }
    }

    fun decreaseCartItemCount(cartItem: CartItem) {
        if (cartItem.count == 1) {
            deleteCartItem(cartItem);return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val info = _cartInfo.value!!
            val list = info.cartItems.toMutableList()
            list[list.indexOf(cartItem)] = cartItem.copy(count = cartItem.count - 1)

            updateCartInfo(info.copy(cartItems = list))
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            var info = _cartInfo.value!!
            info = info.copy(cartItems = info.cartItems.toMutableList().apply { remove(cartItem) })

            updateCartInfo(info)
        }
    }

    private suspend fun updateCartInfo(cartInfo: CartInfo) {
        _cartInfo.emit(cartInfo)
    }
}