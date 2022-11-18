package com.euzhene.productdetailscreen.present

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.euzhene.productdetailscreen.R
import com.euzhene.productdetailscreen.databinding.FragmentProductDetailBinding
import com.euzhene.productdetailscreen.domain.ProductDetail
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.abs

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding

    private val viewModel by viewModels<ProductDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.productDetailList.collectLatest {
                if (it == null) return@collectLatest
                initProductDetails(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initProductDetails(it: ProductDetail) {
        with(binding) {
            vpPictures.adapter = ProductDetailAdapter(it.images)
            vpPictures.offscreenPageLimit = 3
            vpPictures.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(70))
                addTransformer(ViewPager2.PageTransformer { page, position ->
                    val r = 1 - abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                })
            }
            vpPictures.setPageTransformer(compositePageTransformer)


            productCharacteristics.run {
                tvProductName.text = it.title
                ratingBar.rating = it.rating
                btnAddFavorite.setIconResource(
                    if (it.isFavorite)
                        com.euzhene.common.R.drawable.ic_filled_heart
                    else
                        com.euzhene.common.R.drawable.ic_outlined_heart
                )

                tlCharacteristics.addTab(tlCharacteristics.newTab().setText("Shop"))
                tlCharacteristics.addTab(tlCharacteristics.newTab().setText("Details"))
                tlCharacteristics.addTab(tlCharacteristics.newTab().setText("Features"))
                val adapter = ShopAdapter(
                    childFragmentManager,
                    it,
                    lifecycle,
                    tlCharacteristics.tabCount
                )
                vpCharacteristics.adapter = adapter
                tlCharacteristics.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab != null)
                            vpCharacteristics.setCurrentItem(tab.position, true)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}

                })
                vpCharacteristics.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        tlCharacteristics.selectTab(tlCharacteristics.getTabAt(position))
                    }
                })


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProductsDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.btnShop.setOnClickListener {
            setFragmentResult(CART_SELECT_KEY, bundleOf())
        }
        return binding.root
    }

    companion object {
        fun getInstance(): ProductDetailFragment {
            return ProductDetailFragment()
        }

        const val CART_SELECT_KEY = "cart_select"
    }
}