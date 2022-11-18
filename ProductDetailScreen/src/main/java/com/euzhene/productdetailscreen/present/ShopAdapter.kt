package com.euzhene.productdetailscreen.present

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.euzhene.productdetailscreen.domain.ProductDetail

class ShopAdapter(
    private val fm: FragmentManager,
    private val productDetail: ProductDetail,
    lifecyle: Lifecycle,
    private val totalTabs: Int
) : FragmentStateAdapter(fm, lifecyle) {

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ShopFragment.getInstance(productDetail)
        } else {
            Fragment()
        }
    }
}