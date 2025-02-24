package com.example.youtubeplayerapp

import android.util.Log
import com.example.youtubeplayerapp.dataclasses.YoutubeResponse
import retrofit2.Response

class YoutubeRepository {
    suspend fun searchVideos(query :String): Response<YoutubeResponse> {
        Log.d("API_CALL", "Calling searchVideos with: $query")
        if (query.isBlank()) {
            throw IllegalArgumentException("Query cannot be empty")
        }
        return RetrofitClient.instance.searchVideos(query = query, maxResults = 2)
    }
}