package com.sphinx.movies.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sphinx.movies.R
import com.sphinx.movies.util.Constants.Companion.IMAGE_SIZE_SMALL
import com.sphinx.movies.util.Constants.Companion.IMAGE_URL
import com.squareup.picasso.Picasso

class BindingAdapters {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            val img = IMAGE_URL + IMAGE_SIZE_SMALL + url
            Picasso.get().load(img).error(R.drawable.logotype).resize(350, 500).into(view)
        }
    }

}