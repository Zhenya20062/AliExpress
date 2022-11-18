package com.euzhene.productdetailscreen.di

import com.euzhene.productdetailscreen.data.ProductDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideProductDetailService(): ProductDetailService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProductDetailService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5/"
    }
}