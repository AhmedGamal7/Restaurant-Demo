package com.learning.restaurantdemo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learning.restaurantdemo.data.models.Restaurant

@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}