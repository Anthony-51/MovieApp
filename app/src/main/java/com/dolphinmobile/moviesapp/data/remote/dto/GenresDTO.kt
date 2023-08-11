package com.dolphinmobile.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenresDTO(
      @SerializedName("id")
      val id: Int,
      @SerializedName("name")
      val name: String
)