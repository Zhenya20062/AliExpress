package com.euzhene.aliexpress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.euzhene.homescreen.present.HomeFragment
import com.euzhene.mycart.pres.CartFragment
import com.euzhene.productdetailscreen.present.ProductDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AliExpress)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.main_container, HomeFragment.getInstance())
                commit()
            }
        }

            supportFragmentManager.setFragmentResultListener(
                HomeFragment.PRODUCT_SELECT_KEY,
                this
            ) { requestKey, bundle ->
                val res = bundle.getString("bundle_key") ?: throw RuntimeException("No bundle key")
                supportFragmentManager.beginTransaction().apply {
                    setReorderingAllowed(true)
                    addToBackStack(null)
                    replace(R.id.main_container, ProductDetailFragment.getInstance())
                    commit()
                }

            }
            supportFragmentManager.setFragmentResultListener(HomeFragment.CART_SELECT_KEY, this) {_,_->
                supportFragmentManager.beginTransaction().apply {
                    setReorderingAllowed(true)
                    addToBackStack(null)
                    replace(R.id.main_container, CartFragment.getInstance())
                    commit()
                }
            }




    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}