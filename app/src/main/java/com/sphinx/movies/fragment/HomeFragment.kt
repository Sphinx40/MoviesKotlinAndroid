package com.sphinx.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphinx.movies.R
import com.sphinx.movies.adapter.HomeMoviesAdapter
import com.sphinx.movies.data.viewmodel.HomeViewModel
import com.sphinx.movies.data.viewmodel.SharedViewModel
import com.sphinx.movies.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private val mSharedViewModel: SharedViewModel by activityViewModels()
    private val mHomeViewModel: HomeViewModel by activityViewModels()
    private val popularMoviesAdapter: HomeMoviesAdapter by lazy { HomeMoviesAdapter() }
    private val topRatedMoviesAdapter: HomeMoviesAdapter by lazy { HomeMoviesAdapter() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup Recycler View
        setupRecyclerView(binding.root)

        // Observe MutableLiveData
        mHomeViewModel.popularMovies.observe(viewLifecycleOwner, Observer { data ->
            popularMoviesAdapter.setData(data)
        })
        mHomeViewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkData(data)
            topRatedMoviesAdapter.setData(data)
        })

        // Fetch movies
        mHomeViewModel.fetchPopularMovies()
        mHomeViewModel.fetchTopRatedMovies()

        return binding.root
    }

    private fun setupRecyclerView(view: View) {
        view.popularMoviesRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        view.popularMoviesRecyclerView.adapter = popularMoviesAdapter

        view.topRatedRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        view.topRatedRecyclerView.adapter = topRatedMoviesAdapter
    }
}

//override fun onCreateOptionsMenu(menu: Menu): Boolean {
//    menuInflater.inflate(R.menu.menu_search, menu)
//
//    val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//    searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
//    val searchableInfo = searchManager.getSearchableInfo(componentName)
//    searchView?.setSearchableInfo(searchableInfo)
//
//    searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//        override fun onQueryTextSubmit(p0: String): Boolean {
//            val apiService = ServiceBuilder.create()
//            val callSearchMovies = apiService.searchMovies(p0)
//
//            foundMovies.clear()
//            foundMoviesAdapter.clear()
////                progressBar2.visibility = View.VISIBLE
////
////                helpers.getMoviesByRow(foundMovies, callSearchMovies){
////                    println(foundMovies.size)
////                    foundMoviesAdapter.addAll(foundMovies)
////                    progressBar2.visibility = View.INVISIBLE
////                }
//
//            println("SSSSSSSS")
//
//            return true
//        }
//
//        override fun onQueryTextChange(p0: String?): Boolean {
//            return false
//        }
//
//    })
//
//    searchView?.isIconified = false
//    return true
//}