package com.booking.tripsassignment.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.booking.tripsassignment.adapters.BookingsContentAdapter
import com.booking.tripsassignment.data.TripContentData
import com.booking.tripsassignment.databinding.TripEmptyViewBinding
import com.booking.tripsassignment.databinding.TripsListScreenBinding
import com.booking.tripsassignment.repository.TestCase
import com.booking.tripsassignment.utils.LoadingStatus
import com.booking.tripsassignment.utils.Result
import com.booking.tripsassignment.utils.dp2px
import com.booking.tripsassignment.viewmodel.TripViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TripFragment: Fragment() {
    companion object {
        val INPUT_USER_ID = TestCase.PAST_AND_FUTURE_CHAIN.bookerId
    }

    private val viewModel: TripViewModel by viewModels()
    private lateinit var binding: TripsListScreenBinding
    private lateinit var emptyViewBinding: TripEmptyViewBinding
    private val adapter = BookingsContentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TripsListScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initUI()
        loadData()
    }

    private fun loadData() {
        viewModel.fetchData(INPUT_USER_ID)
    }

    private fun initUI() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                if (adapter.data.size == 0) {
                    return
                }
                val pos = parent.getChildAdapterPosition(view)
                if (pos == 0) {
                    context?.let {
                        outRect.top = dp2px(it, 21f)
                    }
                } else {
                    context?.let {
                        outRect.top = dp2px(it, 16f)
                    }
                }
                if (pos == adapter.data.size - 1) {
                    context?.let {
                        outRect.bottom = dp2px(it, 16f)
                    }
                }
            }
        })
        emptyViewBinding = TripEmptyViewBinding.inflate(layoutInflater)
        adapter.setEmptyView(emptyViewBinding.root)
        emptyViewBinding.errorLayout.setOnClickListener{
            loadData()
        }
        adapter.setEnableLoadMore(false)
        showEmptyView(emptyViewBinding.progress)
    }

    private fun initObservers() {
        viewModel.tripsData.observe(this.viewLifecycleOwner) { it ->
            //handle data
            when (it) {
                is LoadingStatus.InProgressLoadingStatus -> {
                    adapter.setNewData(null)
                }
                is LoadingStatus.ResultSuccessStatus -> {
                    adapter.setNewData(it.result.data())
                }

                is LoadingStatus.ResultEmptyDataStatus -> {
                    adapter.setNewData(null)
                }

                is LoadingStatus.ResultFailureStatus -> {
                    adapter.setNewData(null)
                    Toast.makeText(context,  (it.result as Result.Error).exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
            //update ui
            updateUI(it)
        }
    }

    private fun updateUI(loadingStatus:LoadingStatus<MutableList<TripContentData>>) {
        when (loadingStatus) {
            is LoadingStatus.InProgressLoadingStatus -> {
                adapter.setNewData(null)
                showEmptyView(emptyViewBinding.progress)
            }
            is LoadingStatus.ResultSuccessStatus -> {
                showEmptyView(null)
            }

            is LoadingStatus.ResultEmptyDataStatus -> {
                showEmptyView(emptyViewBinding.emptyLayout)
            }

            is LoadingStatus.ResultFailureStatus -> {
                showEmptyView(emptyViewBinding.errorLayout)
            }
            else -> {}
        }
    }

    private fun showEmptyView(view: View?) {
        emptyViewBinding.progress.isVisible = view == emptyViewBinding.progress
        emptyViewBinding.errorLayout.isVisible = view == emptyViewBinding.errorLayout
        emptyViewBinding.emptyLayout.isVisible = view == emptyViewBinding.emptyLayout
    }
}