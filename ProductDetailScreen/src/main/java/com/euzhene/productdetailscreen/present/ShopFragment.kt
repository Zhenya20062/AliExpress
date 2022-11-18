package com.euzhene.productdetailscreen.present

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.euzhene.productdetailscreen.R
import com.euzhene.productdetailscreen.databinding.TabShopBinding
import com.euzhene.productdetailscreen.domain.ProductDetail
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShopFragment : Fragment() {
    private lateinit var binding: TabShopBinding

    private lateinit var product: ProductDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getSerializable(PRODUCT_KEY) as ProductDetail?
            ?: throw RuntimeException("No arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHardware()
        initColors()
        initCapacities()
        initCart()
    }

    private fun initCart() {
        with(binding) {
            tvShopPrice.text = "$${product.price}"
        }
    }

    private fun initCapacities() {
        with(binding) {
            product.capacity.forEach {
                val view = (View.inflate(
                    requireContext(),
                    R.layout.capacity_item,
                    null
                ) as CardView).apply {

                    findViewById<TextView>(R.id.tv_capacity_item).text = it

                    setOnClickListener {
                        changeCapacity(this)
                    }
                }
                view.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                view.updateLayoutParams<LinearLayout.LayoutParams> {
                    setMargins(0, 0, 16, 0)
                }

                llShopCapacity.addView(view)
            }

            ( llShopCapacity.getChildAt(0)as CardView).setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.euzhene.common.R.color.orange
                )
            )
            llShopCapacity.getChildAt(0).findViewById<TextView>(com.euzhene.productdetailscreen.R.id.tv_capacity_item)
                .setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.euzhene.common.R.color.white
                    )
                )
        }
    }

    private fun changeCapacity(view: CardView) {
        binding.llShopCapacity.forEach {
            (it as CardView).apply {
                setCardBackgroundColor(Color.TRANSPARENT)
                findViewById<TextView>(R.id.tv_capacity_item).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.euzhene.common.R.color.gray
                    )
                )
            }
        }
        view.setCardBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                com.euzhene.common.R.color.orange
            )
        )
        view.findViewById<TextView>(R.id.tv_capacity_item).setTextColor(
            ContextCompat.getColor(
                requireContext(),
                com.euzhene.common.R.color.white
            )
        )
    }

    private fun initColors() {
        with(binding) {
            product.color.forEach {
                val view = FloatingActionButton(requireContext()).apply {
                    imageTintList = ColorStateList.valueOf(Color.WHITE)
                    backgroundTintList = ColorStateList.valueOf(Color.parseColor(it))
                    val scale = resources.displayMetrics.density
                    scaleType = ImageView.ScaleType.CENTER
                    compatElevation = 0f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    updateLayoutParams<LinearLayout.LayoutParams> {
                        setMargins(0, 0, 18, 0)
                        val size = (40 * scale).toInt()
                        width = size
                        height = size
                    }
                }
                view.setOnClickListener {
                    changeColor(view)
                }
                llShopColor.addView(view)
            }
            (llShopColor.getChildAt(0) as FloatingActionButton).setImageResource(R.drawable.ic_check)
        }
    }

    private fun changeColor(view: FloatingActionButton) {
        binding.llShopColor.forEach {
            (it as FloatingActionButton).setImageDrawable(null)
        }
        view.setImageResource(R.drawable.ic_check)
    }

    private fun initHardware() {
        with(binding) {
            tvShopCpu.text = product.cpu
            tvShopCamera.text = product.camera
            tvShopRam.text = product.ssd
            tvShopSd.text = product.sd
        }
    }


    companion object {
        fun getInstance(productDetail: ProductDetail): ShopFragment {
            return ShopFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PRODUCT_KEY, productDetail)
                }
            }
        }

        const val PRODUCT_KEY = "product"
    }
}