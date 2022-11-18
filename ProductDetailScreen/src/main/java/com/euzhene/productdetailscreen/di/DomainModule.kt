package com.euzhene.productdetailscreen.di

import com.euzhene.productdetailscreen.data.ProductDetailRepoImpl
import com.euzhene.productdetailscreen.domain.ProductDetailRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindRepo(impl: ProductDetailRepoImpl): ProductDetailRepo
}