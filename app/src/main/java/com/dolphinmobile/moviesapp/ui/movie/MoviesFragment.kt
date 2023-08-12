package com.dolphinmobile.moviesapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dolphinmobile.moviesapp.R
import com.dolphinmobile.moviesapp.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

      private var _binding: FragmentMoviesBinding? = null
      private val binding get() = _binding!!

      private val viewModel: MoviesViewModel by viewModels()
      private lateinit var adapter: MovieAdapter

      override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
      ): View {
            _binding = FragmentMoviesBinding.inflate(inflater, container, false)
            return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initAdapter()

            viewModel.movieState.observe(viewLifecycleOwner){
                  if (it.movies.isNotEmpty()){
                        adapter.add(it.movies)
                  }
            }
      }

      private fun initAdapter(){
            adapter = MovieAdapter(viewType = 1){
                  findNavController().navigate(MoviesFragmentDirections.goToDetail(it))
            }
            binding.rvMovies.adapter = adapter
      }

      override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
      }
}