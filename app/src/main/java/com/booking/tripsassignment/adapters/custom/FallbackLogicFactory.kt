package com.booking.tripsassignment.adapters.custom

import com.booking.tripsassignment.data.TripContentData
import com.chad.library.adapter.base.BaseViewHolder

class FallbackLogicFactory: AbstractCustomHolderFactory(){
    override fun createLogic(): AbstractCustomHolderLogic? {
        return Logic()
    }

    inner class Logic : AbstractCustomHolderLogic() {
        override fun handleHolderLogic(helper: BaseViewHolder?, item: TripContentData?) {
        }
    }
}