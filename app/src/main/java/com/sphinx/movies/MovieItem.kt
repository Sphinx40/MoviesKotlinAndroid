package com.sphinx.movies

import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieItem(val movie: MovieResult): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.title.text = movie.title

        val imageUrl = Config.IMAGE_URL
        val imageSizeSmall = Config.IMAGE_SIZE_SMALL

        Picasso.get()
            .load(imageUrl + imageSizeSmall + movie.poster_path)
            .into(viewHolder.itemView.thumbnail)
    }

    override fun getLayout(): Int {
        return R.layout.movie_card
    }

}