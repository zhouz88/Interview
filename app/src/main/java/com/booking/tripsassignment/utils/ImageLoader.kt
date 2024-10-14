package com.booking.tripsassignment.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Helper to load images using Glide.
 */
object ImageLoader {

    /**
     * Common method to load and cache images using Glide.
     */
    fun loadImage(view: ImageView, image: String) {
        Glide.with(view)
            .load(image)
            .centerCrop()
            .into(view)
    }
}