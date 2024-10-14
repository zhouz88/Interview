package com.booking.tripsassignment.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.booking.tripsassignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Helper to load images using Glide.
 */
object ImageLoader {

    /**
     * Common method to load and cache images using Glide.
     */
    fun loadImage(view: ImageView, image: String) {
        Log.d("testtest", image)
        Glide.with(view)
            .load(image)
            .centerCrop()
            .into(view)
    }
}