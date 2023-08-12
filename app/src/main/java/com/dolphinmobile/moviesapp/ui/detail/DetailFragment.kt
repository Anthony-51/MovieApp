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
import com.dolphinmobile.moviesapp.util.Constants
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
                  if (it.movie != null){
                        binding.tvTitleMovie.text = it.movie.title
                        binding.tvOverview.text = it.movie.overview
                        Glide.with(requireContext()).load("${Constants.IMAGE_URL}${it.movie.posterPath}").into(binding.ivMovie)

                        var genres = ""
                        it.movie.genres.forEach { genre ->
                              genres += "$genre - "
                        }
                        binding.tvGenres.text = genres
                        binding.tvDate.text = it.movie.releaseDate
                  }
            }
      }
}