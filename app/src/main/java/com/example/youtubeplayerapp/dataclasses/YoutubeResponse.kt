package com.example.youtubeplayerapp.dataclasses

import com.google.gson.annotations.SerializedName


data class YoutubeResponse (

  @SerializedName("kind"          ) var kind          : String?          = null,
  @SerializedName("etag"          ) var etag          : String?          = null,
  @SerializedName("nextPageToken" ) var nextPageToken : String?          = null,
  @SerializedName("regionCode"    ) var regionCode    : String?          = null,
  @SerializedName("pageInfo"      ) var pageInfo      : PageInfo?        = PageInfo(),
  @SerializedName("items"         ) var items         : List<Items> = listOf()

)