package com.dolphinmobile.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse401(
    @SerializedName("status_code")
    val code: Int,
    @SerializedName("status_message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)