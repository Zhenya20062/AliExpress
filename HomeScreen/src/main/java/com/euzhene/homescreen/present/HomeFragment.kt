package com.euzhene.homescreen.present

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.euzhene.homescreen.R
import com.euzhene.homescreen.databinding.FragmentHomeBinding
import com.euzhene.homescreen.domain.BestSellerProduct
import com.euzhene.homescreen.domain.HotProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var stopped = false
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initCategories()
        initFilters()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hotProducts.collectLatest {
                if (it == null) return@collectLatest
                initHotSales(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bestSellerProducts.collectLatest {
                if (it == null) return@collectLatest
                initBestSales(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
        initShopCart()
    }

    private fun initShopCart() {
        binding.ivShopCart.setOnClickListener {
            cartSelect()
        }
    }

/*    override fun onStop() {
        super.onStop()
        stopped = true
    }

    override fun onResume() {
        super.onResume()
        if (stopped) {
            viewModel.getProducts()
        }
        stopped = false
    }*/


    private fun initFilters() {
        binding.btnFilterShow.setOnClickListener {
            val bottomSheet = FiltersBottomSheetFragment().apply { filters = viewModel.filters }
            bottomSheet.show(requireActivity().supportFragmentManager, "ModalBottomSheet")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProducts()
    }

    private fun initBestSales(it: List<BestSellerProduct>) {
        binding.rvBestSeller.run {
            addItemDecoration(
                CategoryAdapter.SpacingItemDecorator(
                    horizontalSpace = 30,
                    verticalSpace = 30
                )
            )
            adapter = BestSellerAdapter(it).apply {
                onItemClicked = {
                    productSelect(it.id)
                }
            }
        }
    }

    private fun initHotSales(list: List<HotProduct>) {
        binding.vpHotSales.run {
            adapter = HotSalesAdapter(list).apply {
                onBuyClick = {
                    productSelect(it.id)
                }
            }
        }
    }

    private fun initCategories() {
        binding.rvCategories.run {
            adapter = CategoryAdapter(viewModel.categories)
            addItemDecoration(CategoryAdapter.SpacingItemDecorator(horizontalSpace = 40))
        }
    }

    private fun cartSelect() {
        setFragmentResult(CART_SELECT_KEY, bundleOf())
    }

    private fun productSelect(id: String) {
        setFragmentResult(PRODUCT_SELECT_KEY, bundleOf("bundle_key" to id))
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }

        const val CART_SELECT_KEY = "cart_select"
        const val PRODUCT_SELECT_KEY = "product_select"
    }
}