package com.dolphinmobile.moviesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
      }

      private fun initAdapter(){
            adapter = MovieAdapter(viewType = 2){
                  findNavController().navigate(MoviesFragmentDirections.goToDetail(it))
            }
            binding.rvMovies.adapter = adapter
      }

      override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
      }
}