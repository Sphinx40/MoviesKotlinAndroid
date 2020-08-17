package com.sphinx.movies.adapterItems

import com.sphinx.movies.Config
import com.sphinx.movies.MovieResult
import com.sphinx.movies.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.movie_card.view.*
import kotlinx.android.synthetic.main.movie_row.view.*

class SearchMovieItem(val movie: MovieResult): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.movieTitle.text = movie.title
        view.movieDescription.text = movie.vote_average.toString()

        val imageUrl = Config.IMAGE_URL
        val imageSizeSmall = Config.IMAGE_SIZE_SMALL

        Picasso.get()
            .load(imageUrl + imageSizeSmall + movie.poster_path)
            .into(view.movieImage)
    }

    override fun getLayout(): Int {
        return R.layout.movie_row
    }
}