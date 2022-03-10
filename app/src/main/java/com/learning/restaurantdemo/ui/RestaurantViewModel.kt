package com.learning.restaurantdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.learning.restaurantdemo.repositories.RestaurantRepository


class RestaurantViewModel(repository: RestaurantRepository) : ViewModel() {
    val restaurants = repository.getRestaurants().asLiveData()
}