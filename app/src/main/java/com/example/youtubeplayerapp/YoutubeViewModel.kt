package com.example.youtubeplayerapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeplayerapp.dataclasses.Items
import kotlinx.coroutines.launch

class YoutubeViewModel : ViewModel() {
    private val repository = YoutubeRepository()
    val videosLiveData = MutableLiveData<List<Items>>()

    fun searchVideos(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchVideos(query)
                if (response.isSuccessful) {
                    videosLiveData.postValue(response.body()?.items ?: emptyList())
                } else {
                    Log.e(
                        "API_ERROR",
                        "Error Code: ${response.code()}, Message: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Exception: ${e.localizedMessage}")
            }
        }
    }
}