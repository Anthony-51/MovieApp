package com.dolphinmobile.moviesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dolphinmobile.moviesapp.databinding.FragmentFavoritesBinding
import com.dolphinmobile.moviesapp.ui.movie.MovieAdapter
import com.dolphinmobile.moviesapp.ui.movie.MoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

      private var _binding: FragmentFavoritesBinding? = null
      private val binding get() = _binding!!
      private lateinit var adapter: MovieAdapter
      private val viewModel: FavoritesViewModel by viewModels()

      override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
      ): View {
            _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
            return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initAdapter()

            viewModel.favoriteState.observe(viewLifecycleOwner) {
                  when (it.isLoading) {
                        true -> showLoading()
                        false -> hideLoading()
                  }
                  if (it.movies.isNotEmpty()) {
                        binding.tvError.visibility = View.GONE
                        binding.rvMovies.visibility = View.VISIBLE
                        adapter.add(it.movies)
                  }else binding.rvMovies.visibility = View.GONE
                  if (it.error.isNotEmpty()) {
                        binding.rvMovies.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = it.error
                  } else binding.tvError.visibility = View.GONE
            }
      }

      private fun initAdapter(){
            adapter = MovieAdapter(viewType = 2){
                  findNavController().navigate(MoviesFragmentDirections.goToDetail(it))
            }
            binding.rvMovies.adapter = adapter
      }

      private fun showLoading() {
            binding.progress.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.rvMovies.visibility = View.GONE
      }

      private fun hideLoading() {
            binding.progress.visibility = View.GONE
      }

      override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
      }
}