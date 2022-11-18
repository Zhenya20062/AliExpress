package com.euzhene.homescreen.di

import com.euzhene.homescreen.data.HomeRepoImpl
import com.euzhene.homescreen.domain.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindRepo(impl: HomeRepoImpl):HomeRepo
}