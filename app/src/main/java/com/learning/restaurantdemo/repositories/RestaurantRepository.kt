package com.learning.restaurantdemo.repositories

import androidx.room.withTransaction
import com.learning.restaurantdemo.api.RestaurantApi
import com.learning.restaurantdemo.data.room.RestaurantDataBase
import com.learning.restaurantdemo.utils.networkBoundResource

class RestaurantRepository(
    private val api: RestaurantApi,
    private val db: RestaurantDataBase
) {

    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getRestaurants()
        },
        fetch = {
            api.getRestaurants()
        },
        saveFetchResult = {restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurant()
                restaurantDao.insertRestaurants(restaurants)
            }
        }
    )
}