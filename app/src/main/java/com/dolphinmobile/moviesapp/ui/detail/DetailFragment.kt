package com.dolphinmobile.moviesapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dolphinmobile.moviesapp.R
import com.dolphinmobile.moviesapp.databinding.FragmentDetailBinding
import com.dolphinmobile.moviesapp.domain.model.Movie
import com.dolphinmobile.moviesapp.util.Constants
import com.dolphinmobile.moviesapp.util.DateFormat.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

      private var _binding: FragmentDetailBinding? = null
      private val binding get() = _binding!!

      private val args: DetailFragmentArgs by navArgs()
      private val movieId by lazy { args.movieId }


      private val viewModel : DetailViewModel by viewModels()
      override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
      ): View {
            // Inflate the layout for this fragment
            _binding = FragmentDetailBinding.inflate(inflater, container, false)
            return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewModel.getMovieBy(movieId)

            binding.ivFavorite.setOnClickListener {
                  if (binding.ivFavorite.isSelected){
                        viewModel.removeMovie()
                  }else{
                        viewModel.addMovie()
                  }
                  binding.ivFavorite.isSelected = !binding.ivFavorite.isSelected
            }

            viewModel.detailState.observe(viewLifecycleOwner){
                  when (it.isLoading) {
                        true -> showLoading()
                        false -> hideLoading()
                  }
                  if (it.movie != null){
                        processMovie(it.movie)
                  }
            }
      }

      private fun processMovie(movie: Movie) {
            binding.tvTitleMovie.text = movie.title
            binding.tvOverview.text = movie.overview
            binding.ivFavorite.isSelected = movie.isFavorite
            Glide.with(requireContext()).load("${Constants.IMAGE_URL}${movie.posterPath}")
                  .into(binding.ivMovie)
            Glide.with(requireContext()).load("${Constants.IMAGE_URL}${movie.posterPath}")
                  .override(10, 10).into(binding.ivBackground)

            var genres = ""
            movie.genres.forEachIndexed { index, genre ->
                  genres += "$genre ${if (index < movie.genres.size - 1) "- " else ""}"
            }
            binding.tvGenres.text = genres
            binding.tvDate.text = movie.releaseDate.formatDate("dd MMMM yyyy")
            binding.rbAverage.rating = movie.voteAverage.toFloat()
      }


      private fun showLoading() {
            binding.progress.visibility = View.VISIBLE
            binding.clHeader.visibility = View.GONE
            binding.clBody.visibility = View.GONE
      }

      private fun hideLoading() {
            binding.progress.visibility = View.GONE
            binding.clHeader.visibility = View.VISIBLE
            binding.clBody.visibility = View.VISIBLE
      }

      override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
      }
}