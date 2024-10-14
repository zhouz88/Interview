package com.booking.tripsassignment.adapters

import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import com.booking.tripsassignment.adapters.custom.AbstractCustomHolderFactory
import com.booking.tripsassignment.adapters.custom.CardLogicFactory
import com.booking.tripsassignment.adapters.custom.FallbackLogicFactory
import com.booking.tripsassignment.adapters.custom.HeaderLogicFactory
import com.booking.tripsassignment.data.TripContentData
import com.booking.tripsassignment.databinding.TripCardItemLayoutBinding
import com.booking.tripsassignment.databinding.TripsHeaderItemLayoutBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class BookingsContentAdapter : BaseQuickAdapter<TripContentData, BaseViewHolder>(null) {

    companion object {
        val HEAD_TYPE = 0
        val CARD_TYPE = 1

        val HEAD_TITLE_UPCOMING = "upcoming"
        val HEAD_TITLE_PAST = "past"
    }

    val holderFactoryCache: SparseArray<AbstractCustomHolderFactory> = SparseArray()
    override fun getDefItemViewType(position: Int): Int {
        return when (data[position]) {
            is TripContentData.TripHeadItem -> HEAD_TYPE
            else -> CARD_TYPE
        }
    }

    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEAD_TYPE -> {
                val binding = TripsHeaderItemLayoutBinding.inflate(LayoutInflater.from(mContext),parent, false)
                HeadViewHolder(binding)
            }
            else -> {
                val binding = TripCardItemLayoutBinding.inflate(LayoutInflater.from(mContext), parent, false);
                CardViewHolder(binding)
            }
        }
    }

    override fun convert(helper: BaseViewHolder, item: TripContentData?) {
        kotlin.runCatching {
            val holderFactory = getCustomHolderFactoryByName(helper, item)
            holderFactory.createLogic()?.handleHolderLogic(helper, item)
        }.onFailure {
            Log.d("Adapter", it.message ?: "error")
        }
    }

    private fun getCustomHolderFactoryByName(helper: BaseViewHolder, item: TripContentData?):
            AbstractCustomHolderFactory {
        if (holderFactoryCache.get(helper.itemViewType) == null) {
            holderFactoryCache.put(helper.itemViewType, when (helper.itemViewType) {
                HEAD_TYPE -> {
                     HeaderLogicFactory()
                }

                CARD_TYPE -> {
                    CardLogicFactory()
                }
                else -> {
                    //fall back
                    FallbackLogicFactory()
                }
            })
        }
        return holderFactoryCache.get(helper.itemViewType)
    }


    data class CardViewHolder(val binding: TripCardItemLayoutBinding) : BaseViewHolder(binding.root)
    data class HeadViewHolder(val binding: TripsHeaderItemLayoutBinding) : BaseViewHolder(binding.root)
}