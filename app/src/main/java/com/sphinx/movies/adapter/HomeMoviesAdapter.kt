package com.sphinx.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sphinx.movies.data.model.Movie
import com.sphinx.movies.databinding.MovieCardBinding
import com.sphinx.movies.diffUtil.MovieDiffUtil

class HomeMoviesAdapter : RecyclerView.Adapter<HomeMoviesViewHolder>() {

    private var dataList = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieCardBinding.inflate(layoutInflater, parent, false)
        return HomeMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeMoviesViewHolder, position: Int) {
        val movie = dataList[position]
        holder.bind(movie)
    }

    fun setData(movies: List<Movie>){
        val toDoDiffUtil = MovieDiffUtil(dataList, movies)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = movies
        toDoDiffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = dataList.size
}

class HomeMoviesViewHolder(private val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie){
        binding.movie = movie
        binding.executePendingBindings()
    }
}