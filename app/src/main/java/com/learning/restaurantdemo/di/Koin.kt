package com.learning.restaurantdemo.di

import android.app.Application
import androidx.room.Room
import com.learning.restaurantdemo.api.RestaurantApi
import com.learning.restaurantdemo.data.room.RestaurantDataBase
import com.learning.restaurantdemo.repositories.RestaurantRepository
import com.learning.restaurantdemo.ui.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {


    single { provideRetrofit() }

    single { provideRestaurantApi(get()) }

    single { provideDataBase(get()) }

    single { RestaurantRepository(get(), get()) }

    viewModel { RestaurantViewModel(get()) }
}

fun provideDataBase(app: Application): RestaurantDataBase =
    Room.databaseBuilder(app, RestaurantDataBase::class.java, "RestaurantDataBse")
        .build()

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(RestaurantApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
    retrofit.create(RestaurantApi::class.java)