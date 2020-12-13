package com.sphinx.movies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.sphinx.movies.R
import com.sphinx.movies.util.Constants.Companion.IMAGE_SIZE_SMALL
import com.sphinx.movies.util.Constants.Companion.IMAGE_URL
import com.squareup.picasso.Picasso

class BindingAdapters {

    companion object {
        @BindingAdapter("android:loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            val img = IMAGE_URL + IMAGE_SIZE_SMALL + url
            Picasso.get().load(img).error(R.drawable.logotype).resize(200, 280).into(view)
        }

        @BindingAdapter("android:visibleIfDataReceived")
        @JvmStatic
        fun visibleIfDataReceived(view: View, dataReceived: Boolean) {
            view.visibility = if (dataReceived) View.VISIBLE else View.INVISIBLE
        }

        @BindingAdapter("android:showLoading")
        @JvmStatic
        fun showLoading(view: ProgressBar, dataReceived: Boolean) {
            println(dataReceived)
            view.visibility = if (dataReceived) View.INVISIBLE else View.VISIBLE
        }
    }

}