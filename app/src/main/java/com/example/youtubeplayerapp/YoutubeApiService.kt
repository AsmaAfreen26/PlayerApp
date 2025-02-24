package com.example.youtubeplayerapp

import com.example.youtubeplayerapp.dataclasses.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface YoutubeApiService {
    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") apiKey: String = "AIzaSyDroeylCD_Fn1HBT25dKcmtcSum-AQs0NQ",
        @Query("maxResults") maxResults: Int = 5
    ): Response<YoutubeResponse>
}
