package com.euzhene.mycart.di

import com.euzhene.mycart.data.CartService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun getCartService(): CartService {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(CartService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149/"
    }
}