package com.booking.tripsassignment.adapters.custom

import com.booking.tripsassignment.R
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_TITLE_PAST
import com.booking.tripsassignment.adapters.BookingsContentAdapter.Companion.HEAD_TITLE_UPCOMING
import com.booking.tripsassignment.adapters.BookingsContentAdapter.HeadViewHolder
import com.booking.tripsassignment.data.TripContentData
import com.chad.library.adapter.base.BaseViewHolder

class HeaderLogicFactory: AbstractCustomHolderFactory() {
    override fun createLogic(): AbstractCustomHolderLogic {
        return Logic()
    }

    inner class Logic : AbstractCustomHolderLogic() {
        override fun handleHolderLogic(helper: BaseViewHolder?, item: TripContentData?) {
            val binding = (helper as HeadViewHolder).binding
            item?.let {
                val title = (it as TripContentData.TripHeadItem).data
                when (title) {
                    HEAD_TITLE_UPCOMING -> {
                        binding.tripsHeader.setText(R.string.upcoming_trips)
                    }
                    HEAD_TITLE_PAST -> {
                        binding.tripsHeader.setText(R.string.past_trips)
                    }
                    else -> {}
                }
            }
        }
    }
}