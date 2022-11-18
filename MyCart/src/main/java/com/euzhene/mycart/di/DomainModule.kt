package com.euzhene.mycart.di

import com.euzhene.mycart.data.CartRepoImpl
import com.euzhene.mycart.domain.CartRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindCartRepo(impl: CartRepoImpl):CartRepo
}