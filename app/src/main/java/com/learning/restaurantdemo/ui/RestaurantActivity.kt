package com.learning.restaurantdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.imageapplication.databinding.ActivityRestaurantBinding
import com.learning.restaurantdemo.data.adapters.RestaurantAdapter
import com.learning.restaurantdemo.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantActivity : AppCompatActivity() {

    private val restaurantViewModel: RestaurantViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()
        binding.apply {

            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
            }

            restaurantViewModel.restaurants.observe(this@RestaurantActivity) { result ->

                restaurantAdapter.submitList(result.data)


                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()


                textViewCannotLoad.isVisible =
                    result is Resource.Error && result.data.isNullOrEmpty()
                textViewCannotLoad.text = result.error?.localizedMessage
            }
        }
    }
}