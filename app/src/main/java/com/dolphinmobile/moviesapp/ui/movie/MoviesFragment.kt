package com.dolphinmobile.moviesapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dolphinmobile.moviesapp.R
import com.dolphinmobile.moviesapp.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

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

            binding.etFilter.addTextChangedListener { editable ->
                  editable?.let {
                      if (editable.toString().trim().isNotEmpty()){
                            binding.ivResetFilter.visibility = View.VISIBLE
                            viewModel.filterMovies(editable.toString())
                      }else {
                            binding.ivResetFilter.visibility = View.GONE
                            viewModel.resetMovies()
                      }
                  }
            }
            binding.ivResetFilter.setOnClickListener {
                  binding.etFilter.setText("")
                  it.visibility = View.GONE
            }
            viewModel.movieState.observe(viewLifecycleOwner){
                  if (it.movies.isNotEmpty()){
                        binding.rvMovies.visibility = View.VISIBLE
                        if (it.filterMovies.isNotEmpty()){
                              adapter.add(it.filterMovies)
                        }else adapter.add(it.movies)
                  }else{
                        binding.rvMovies.visibility = View.GONE
                  }

                  if (it.error.isNotEmpty()) {
                        binding.rvMovies.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = it.error
                  }else{
                        binding.tvError.visibility = View.GONE
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