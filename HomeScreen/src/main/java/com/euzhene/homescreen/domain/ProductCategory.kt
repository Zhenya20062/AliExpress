package com.euzhene.homescreen.domain

import androidx.annotation.DrawableRes

data class ProductCategory(
    val name:String,
    @DrawableRes val iconId:Int,
    val active:Boolean,
)
