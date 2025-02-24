package com.example.youtubeplayerapp.dataclasses

import com.google.gson.annotations.SerializedName


data class PageInfo (

  @SerializedName("totalResults"   ) var totalResults   : Int? = null,
  @SerializedName("resultsPerPage" ) var resultsPerPage : Int? = null

)