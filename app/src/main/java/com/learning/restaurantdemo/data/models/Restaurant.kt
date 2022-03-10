package com.learning.restaurantdemo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Restaurant")
data class Restaurant(
    @PrimaryKey
    val name: String,
    val type: String,
    val logo: String,
    val address: String
)