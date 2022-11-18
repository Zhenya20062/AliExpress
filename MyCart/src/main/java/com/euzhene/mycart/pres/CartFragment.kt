package com.euzhene.mycart.pres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.euzhene.mycart.data.CartService
import com.euzhene.mycart.databinding.FragmentCartBinding
import com.euzhene.mycart.domain.CartInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    private val viewModel by viewModels<CartViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCartInfo()
        lifecycleScope.launchWhenStarted {
            viewModel.cartInfo.collectLatest {
                if (it == null) return@collectLatest
                initCartItem(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
        initToolbar()
        initAdapter()
    }

    private fun initAdapter() {
        with(binding) {
            rvCart.addItemDecoration(CartItemDecoration(45))
            rvCart.adapter = CartAdapter().apply {
                onItemDelete = {
                    viewModel.deleteCartItem(it)
                }
                onItemCountDecrease = {
                    viewModel.decreaseCartItemCount(it)
                }
                onItemCountIncrease = {
                    viewModel.increaseCartItemCount(it)
                }

            }
        }
    }

    private fun initCartItem(cartInfo: CartInfo) {
        with(binding) {
            this.tvTotalPrice.text = "$${cartInfo.total} us"
            tvDeliveryPrice.text = cartInfo.delivery
            (rvCart.adapter as CartAdapter).submitList(cartInfo.cartItems)
        }
    }


    private fun initToolbar() {
        with(binding) {
            toolbar.title = ""
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun getInstance(): CartFragment {
            return CartFragment()
        }
    }
}