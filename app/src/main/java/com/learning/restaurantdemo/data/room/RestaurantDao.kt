package com.learning.restaurantdemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learning.restaurantdemo.data.models.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(list: List<Restaurant>)

    @Query("DELETE FROM Restaurant")
    suspend fun deleteAllRestaurant()

    @Query("SELECT * FROM Restaurant")
    fun getRestaurants(): Flow<List<Restaurant>>

}