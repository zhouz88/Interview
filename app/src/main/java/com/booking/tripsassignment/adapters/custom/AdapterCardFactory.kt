package com.booking.tripsassignment.adapters.custom

import com.booking.tripsassignment.data.TripContentData
import com.chad.library.adapter.base.BaseViewHolder

abstract class AbstractCustomHolderFactory {
    abstract fun createLogic(): AbstractCustomHolderLogic?
}

abstract class AbstractCustomHolderLogic {
    abstract fun handleHolderLogic(helper: BaseViewHolder?, item: TripContentData?)
}
