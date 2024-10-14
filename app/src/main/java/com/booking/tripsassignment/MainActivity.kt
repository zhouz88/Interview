package com.booking.tripsassignment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.booking.tripsassignment.databinding.ActivityMainScreenBinding
import com.booking.tripsassignment.utils.LoadingStatus
import com.booking.tripsassignment.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        initUI()
        loadData()
    }

    private fun loadData() {
        viewModel.fetchData(33333)
    }

    private fun initUI() {

    }

    private fun initObservers() {
        viewModel.tripsData.observe(this) { it ->
            when (it) {
                is LoadingStatus.InProgressLoadingStatus -> {

                }
                is LoadingStatus.ResultSuccessStatus -> {

                }

                is LoadingStatus.ResultEmptyDataStatus -> {

                }

                is LoadingStatus.ResultFailureStatus -> {

                }
                else -> {}
            }
        }
    }
}