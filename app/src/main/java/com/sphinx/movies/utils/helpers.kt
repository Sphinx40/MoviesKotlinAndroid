package com.sphinx.movies.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sphinx.movies.adapterItems.HomeMovieItem
import com.sphinx.movies.Movies
import com.sphinx.movies.adapterItems.SearchMovieItem
import com.sphinx.movies.fragments.RecommendationsFragment
import com.sphinx.movies.fragments.UpcomingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object helpers {

    class TabLayoutFragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> RecommendationsFragment()
                else -> UpcomingFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Сейчас смотрят"
                else -> "Предстоящий"
            }
        }
    }

    fun getMoviesBySmallCard(list: ArrayList<HomeMovieItem>, callMovies: Call<Movies>) {
        callMovies.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == 200) {
                    val res = response.body()!!.results
                    for (movie in res) {
                        val movieItem = HomeMovieItem(movie)
                        list.add(movieItem)
                    }
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                println(t.message)
            }
        })
    }

    fun getMoviesByRow(list: ArrayList<SearchMovieItem>, callMovies: Call<Movies>, callback: ((result: Boolean?) -> Unit)? = null) {

        callMovies.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == 200) {
                    val res = response.body()!!.results

                    for (movie in res) {
                        val movieItem = SearchMovieItem(movie)
                        list.add(movieItem)
                    }
                    callback?.invoke(true)
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                println(t.message)
                callback?.invoke(false)
            }
        })
    }
}