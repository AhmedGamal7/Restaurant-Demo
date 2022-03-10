package com.learning.restaurantdemo.api

import com.learning.restaurantdemo.data.models.Restaurant
import retrofit2.http.GET

interface RestaurantApi {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=50")
    suspend fun getRestaurants(): List<Restaurant>
}