package com.sphinx.movies.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphinx.movies.*
import com.sphinx.movies.activities.MainActivity
import com.sphinx.movies.activities.MovieDetailsActivity
import com.sphinx.movies.services.ServiceBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val popularMoviesAdapter = GroupAdapter<GroupieViewHolder>()
    private val topRatedMoviesAdapter = GroupAdapter<GroupieViewHolder>()

    private val popularMovies = Globals.popularMovies
    private val topRatedMovies = Globals.topRatedMovies


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as MainActivity).supportActionBar?.title = "Домой"

        view.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        view.recyclerView.adapter = popularMoviesAdapter

        view.recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        view.recyclerView2.adapter = topRatedMoviesAdapter

        if ((popularMovies.size == 0) or (topRatedMovies.size == 0)) {
            val apiService = ServiceBuilder.create()
            val callPopularMovies = apiService.fetchPopularMovies()
            val callTopRatedMovies = apiService.fetchTopRatedMovies()

            getMovies(popularMoviesAdapter, popularMovies, callPopularMovies)
            getMovies(topRatedMoviesAdapter, topRatedMovies, callTopRatedMovies)
        } else {
            popularMoviesAdapter.addAll(popularMovies)
            topRatedMoviesAdapter.addAll(topRatedMovies)

            popularMoviesAdapter.notifyDataSetChanged()
            topRatedMoviesAdapter.notifyDataSetChanged()
        }

        onClickListener(popularMoviesAdapter)
        onClickListener(topRatedMoviesAdapter)

        return view
    }

    private fun getMovies(moviesAdapter: GroupAdapter<GroupieViewHolder>, list: ArrayList<MovieItem>, callMovies: Call<Movies>) {
        callMovies.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == 200) {
                    val res = response.body()!!.results
                    for (movie in res) {
                        val movieItem = MovieItem(movie)
                        moviesAdapter.add(movieItem)
                        list.add(movieItem)
                    }
                    moviesAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun onClickListener(adapter: GroupAdapter<GroupieViewHolder>) {
        adapter.setOnItemClickListener { item, _ ->
            val movieItem = item as MovieItem
            val intent = Intent(this.context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movieItem.movie.id)
            startActivity(intent)
        }
    }
    
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}